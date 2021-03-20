package com.example.pokemongo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pokemongo.data.model.PokemonInfoItemModel
import com.example.pokemongo.data.model.PokemonInfoModel
import com.example.pokemongo.data.model.PokemonModel
import com.example.pokemongo.data.repository.DetailRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailViewModel constructor(application: Application, private val detailRepository: DetailRepository) : AndroidViewModel(application) {
    val isLoading = MutableLiveData<Boolean>()
    val pokemonModel = MutableLiveData<PokemonModel>()
    val pokemonInfoItemModel = MutableLiveData<PokemonInfoItemModel>()

    fun getPokemonInfo() {
        isLoading.value = true
        detailRepository.getPokemonInfo(pokemonModel.value!!.name)
            .subscribe({
                updateImgList(it.sprites)
                isLoading.value = false

                replacePokemonInfo(it.sprites)
        }, {
                getPokemonInfoDao()
                isLoading.value = false
        })
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

    private fun getPokemonInfoDao() = detailRepository.getPokemonInfoDao(pokemonModel.value!!.name)
            .subscribe({
                updateImgList(it)
            }, {})

    private fun replacePokemonInfo(model: PokemonInfoModel) = GlobalScope.launch {
        detailRepository.replacePokemonInfo(model, pokemonModel.value!!.name)
    }
}