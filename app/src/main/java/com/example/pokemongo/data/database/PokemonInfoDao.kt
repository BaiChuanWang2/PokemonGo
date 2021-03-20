package com.example.pokemongo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pokemongo.data.model.PokemonInfoModel
import io.reactivex.Single

@Dao
interface PokemonInfoDao {
    @Query("SELECT * FROM PokemonInfoModel WHERE name LIKE '%' || :name || '%'")
    fun getPokemonInfo(name: String): Single<PokemonInfoModel>

    @Query("SELECT * FROM PokemonInfoModel WHERE name = :name")
    fun getPokemonInfoByName(name: String): PokemonInfoModel?

    @Insert
    fun insertPokemonInfo(model: PokemonInfoModel)

    @Update
    fun updatePokemonInfo(model: PokemonInfoModel)

    fun replacePokemonInfo(model: PokemonInfoModel, name: String) {
        getPokemonInfoByName(name).apply {
            this?.apply {
                updatePokemonInfo(model)
            } ?: apply {
                model.name = name
                insertPokemonInfo(model)
            }
        }
    }
}