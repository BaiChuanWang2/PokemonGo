package com.example.pokemongo.data.database

import androidx.room.*
import androidx.paging.PagingSource
import com.example.pokemongo.data.model.PokemonModel

@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonModel")
    fun getPokemon(): PagingSource<Int, PokemonModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(models: ArrayList<PokemonModel>)

    @Query("DELETE FROM PokemonModel")
    suspend fun clearPokemon()

    @Query("SELECT * FROM PokemonModel ORDER BY nextPage DESC LIMIT 1")
    suspend fun getLastRow(): PokemonModel?
}