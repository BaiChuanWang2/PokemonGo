package com.example.pokemongo.data.repository

import com.example.pokemongo.data.api.RetrofitInterface
import com.example.pokemongo.data.database.AppDataBase
import com.example.pokemongo.data.model.PokemonInfoModel
import kotlinx.coroutines.flow.flow

class DetailRepository constructor(private val retrofitInterface: RetrofitInterface, private val appDataBase: AppDataBase) {
    fun getPokemonInfo(name: String) = flow {
        emit(retrofitInterface.getPokemonInfo(name))
    }

    fun getPokemonInfoDao(name: String) = flow {
        emit(appDataBase.pokemonInfoDao().getPokemonInfo(name))
    }

    fun replacePokemonInfo(model: PokemonInfoModel, name: String) = appDataBase.pokemonInfoDao().replacePokemonInfo(model, name)
}