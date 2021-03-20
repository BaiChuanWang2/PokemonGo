package com.example.pokemongo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokemongo.data.model.PokemonInfoModel
import com.example.pokemongo.data.model.PokemonModel

@Database(entities = [PokemonModel::class, PokemonInfoModel::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonInfoDao(): PokemonInfoDao

    companion object {
        fun initAppDataBase(context: Context) = Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase::class.java.simpleName).build()
    }
}