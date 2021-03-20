package com.example.pokemongo.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.pokemongo.R
import com.example.pokemongo.data.SingleLiveEvent
import com.example.pokemongo.data.model.PokemonModel
import com.example.pokemongo.data.repository.PokemonRepository

class PokemonViewModel(application: Application, pokemonRepository: PokemonRepository) : AndroidViewModel(application) {
    val pokemonModelList = pokemonRepository.getPokeMon()
        .cachedIn(viewModelScope)
        .asLiveData()
    val itemIntent = SingleLiveEvent<PokemonModel>()

    fun onClick(view: View) {
        when (view.id) {
            R.id.cv -> {
                itemIntent.value = view.tag as PokemonModel
            }
        }
    }
}