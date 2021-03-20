package com.example.pokemongo.data.repository

import com.example.pokemongo.data.api.RetrofitInterface
import com.example.pokemongo.data.database.AppDataBase
import com.example.pokemongo.data.model.PokemonInfoModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.newThread
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread

class DetailRepository constructor(private val retrofitInterface: RetrofitInterface, private val appDataBase: AppDataBase) {
    fun getPokemonInfo(name: String) = retrofitInterface.getPokemonInfo(name)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())!!

    fun getPokemonInfoDao(name: String) = appDataBase.pokemonInfoDao().getPokemonInfo(name)
            .subscribeOn(newThread())
            .observeOn(mainThread())

    fun replacePokemonInfo(model: PokemonInfoModel, name: String) = appDataBase.pokemonInfoDao().replacePokemonInfo(model, name)
}