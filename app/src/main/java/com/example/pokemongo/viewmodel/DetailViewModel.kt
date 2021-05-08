package com.example.pokemongo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemongo.data.model.PokemonInfoItemModel
import com.example.pokemongo.data.model.PokemonInfoModel
import com.example.pokemongo.data.model.PokemonModel
import com.example.pokemongo.data.repository.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel constructor(application: Application, private val detailRepository: DetailRepository) : AndroidViewModel(application) {
    val isLoading = MutableLiveData<Boolean>()
    val pokemonModel = MutableLiveData<PokemonModel>()
    val pokemonInfoItemModel = MutableLiveData<PokemonInfoItemModel>()

    fun getPokemonInfo() = viewModelScope.launch {
        detailRepository.getPokemonInfo(pokemonModel.value!!.name)
            .onStart {
                isLoading.value = true
            }
            .catch {
                getPokemonInfoDao()
                isLoading.value = false
            }
            .collect {
                updateImgList(it.sprites)
                isLoading.value = false

                replacePokemonInfo(it.sprites)
            }
    }

    private fun updateImgList(infoModel: PokemonInfoModel) {
        infoModel.apply {
            val imgModels = ArrayList<PokemonInfoItemModel.ImgModel>()
            imgModels.add(PokemonInfoItemModel.ImgModel(front_default))
            imgModels.add(PokemonInfoItemModel.ImgModel(front_shiny))
            imgModels.add(PokemonInfoItemModel.ImgModel(back_default))
            imgModels.add(PokemonInfoItemModel.ImgModel(back_shiny))
            pokemonInfoItemModel.value = PokemonInfoItemModel(imgModels = imgModels)
        }
    }

    private fun getPokemonInfoDao() = viewModelScope.launch {
        detailRepository.getPokemonInfoDao(pokemonModel.value!!.name)
            .collect {
                updateImgList(it)
            }
    }

    private fun replacePokemonInfo(model: PokemonInfoModel) = viewModelScope.launch(Dispatchers.IO) {
        detailRepository.replacePokemonInfo(model, pokemonModel.value!!.name)
    }
}