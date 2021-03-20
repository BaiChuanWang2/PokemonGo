package com.example.pokemongo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemongo.data.model.PokemonModel
import com.example.pokemongo.databinding.AdapterPokemonBinding
import com.example.pokemongo.viewmodel.PokemonViewModel

class PokemonAdapter constructor(context: Context, private val viewModel: PokemonViewModel) : PagingDataAdapter<PokemonModel, PokemonAdapter.ViewHolder>(DiffCallback) {
    private val inflater = LayoutInflater.from(context)

    class ViewHolder constructor(private val binding: AdapterPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(viewModel: PokemonViewModel, dataModel: PokemonModel) {
            binding.apply {
                this.viewModel = viewModel
                this.dataModel = dataModel
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterPokemonBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.bindView(viewModel, this)
        }
    }

    object DiffCallback: DiffUtil.ItemCallback<PokemonModel>() {
        override fun areItemsTheSame(oldItem: PokemonModel, newItem: PokemonModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PokemonModel, newItem: PokemonModel): Boolean {
            return oldItem == newItem
        }
    }
}