package com.example.pokemongo.di

import com.example.pokemongo.data.database.AppDataBase
import com.example.pokemongo.data.repository.DetailRepository
import com.example.pokemongo.data.repository.PokemonRepository
import com.example.pokemongo.viewmodel.PokemonViewModel
import com.example.pokemongo.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDataBase.initAppDataBase(get()) }
    viewModel { PokemonViewModel(get(), get()) }
    single { PokemonRepository(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
    single { DetailRepository(get(), get()) }
}