package com.example.pokemongo.data.repository.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pokemongo.data.api.RetrofitInterface
import com.example.pokemongo.data.database.AppDataBase
import com.example.pokemongo.data.model.PokemonModel
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(private val retrofitInterface: RetrofitInterface, private val appDataBase: AppDataBase) : RemoteMediator<Int, PokemonModel>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, PokemonModel>): MediatorResult {
        return try {
            val pageKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val lastItem = appDataBase.withTransaction {
                        appDataBase.pokemonDao().getLastRow()
                    } ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.nextPage
                }
            }
            val page = pageKey ?: 0
            val response = retrofitInterface.getPokemon(state.config.pageSize, page * state.config.pageSize).results
            response.forEach {
                it.nextPage = page + 1
            }
            appDataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDataBase.pokemonDao().clearPokemon()
                }
                appDataBase.pokemonDao().insertPokemon(response)
            }

            val endOfPaginationReached = response.isEmpty()
            MediatorResult.Success(endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}