package com.example.pokemongo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemongo.data.model.PokemonInfoItemModel
import com.example.pokemongo.databinding.AdapterPokemonInfoBinding
import com.example.pokemongo.viewmodel.DetailViewModel

class PokemonInfoAdapter constructor(context: Context, private val viewModel: DetailViewModel) : RecyclerView.Adapter<PokemonInfoAdapter.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    class ViewHolder constructor(private val binding: AdapterPokemonInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(dataModel: PokemonInfoItemModel.ImgModel) {
            binding.apply {
                this.dataModel = dataModel
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterPokemonInfoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = viewModel.pokemonInfoItemModel.value!!.imgModels[position]
        holder.bindView(dataModel)
    }

    override fun getItemCount(): Int {
        return viewModel.pokemonInfoItemModel.value?.imgModels?.count() ?: 0
    }
}