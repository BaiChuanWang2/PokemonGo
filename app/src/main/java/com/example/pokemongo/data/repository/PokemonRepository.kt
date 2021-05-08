package com.example.pokemongo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pokemongo.data.api.RetrofitInterface
import com.example.pokemongo.data.database.AppDataBase
import com.example.pokemongo.data.repository.remotemediator.PokemonRemoteMediator

class PokemonRepository constructor(private val retrofitInterface: RetrofitInterface, private val appDataBase: AppDataBase) {
    @OptIn(ExperimentalPagingApi::class)
    fun getPokeMon() = Pager(PagingConfig(pageSize = 20,
            enablePlaceholders = true,
            prefetchDistance = 4,
            initialLoadSize = 20),
            remoteMediator = PokemonRemoteMediator(retrofitInterface, appDataBase)) {
        appDataBase.pokemonDao().getPokemon()
    }.flow
}