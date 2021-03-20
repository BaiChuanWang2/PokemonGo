package com.example.pokemongo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemongo.R
import com.example.pokemongo.common.common.ExtraUtils
import com.example.pokemongo.databinding.FragmentPokemonBinding
import com.example.pokemongo.ui.adapter.PokemonAdapter
import com.example.pokemongo.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonFragment : BaseFragment() {
    private lateinit var binding: FragmentPokemonBinding
    private val viewModel: PokemonViewModel by viewModel()
    private val adapter by lazy { PokemonAdapter(requireContext(), viewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentPokemonBinding.inflate(inflater).let {
            binding = it
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.apply {
            rv.layoutManager = GridLayoutManager(requireContext(), 2)
            rv.adapter = adapter
        }
        viewModel.apply {
            pokemonModelList.observe(viewLifecycleOwner, {
                adapter.submitData(lifecycle, it)
            })
            itemIntent.observe(viewLifecycleOwner, {
                Bundle().apply {
                    putParcelable(ExtraUtils.EXTRA_DATA, it)
                    findNavController().navigate(R.id.nav_detail, this)
                }
            })
        }
    }
}