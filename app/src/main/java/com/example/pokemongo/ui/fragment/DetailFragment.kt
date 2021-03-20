package com.example.pokemongo.ui.fragment

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.daimajia.numberprogressbar.NumberProgressBar
import com.example.pokemongo.R
import com.example.pokemongo.common.common.ExtraUtils
import com.example.pokemongo.common.common.Utils
import com.example.pokemongo.databinding.FragmentDetailBinding
import com.example.pokemongo.ui.adapter.PokemonInfoAdapter
import com.example.pokemongo.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private val adapter by lazy { PokemonInfoAdapter(requireContext(), viewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentDetailBinding.inflate(inflater).let {
            binding = it
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBundle()
        initUI()
        initToolbar(binding.toolbar)
        viewModel.getPokemonInfo()
    }

    private fun initBundle() {
        arguments?.apply {
            viewModel.pokemonModel.value = getParcelable(ExtraUtils.EXTRA_DATA)
        }
    }

    private fun initUI() {
        binding.apply {
            val srcUrl = Utils.getSrcUrl(viewModel!!.pokemonModel.value!!.url)
            Glide.with(this@DetailFragment).load(srcUrl).placeholder(R.drawable.ic_baseline_image_24).into(iv)

            rv.layoutManager = GridLayoutManager(requireContext(), 4)
            rv.adapter = adapter
        }
        viewModel.apply {
            pokemonInfoItemModel.observe(viewLifecycleOwner, {
                adapter.notifyDataSetChanged()
                updatePb(binding.pbHp, it.hp, it.maxHp)
                updatePb(binding.pbAttack, it.attack, it.maxAttack)
                updatePb(binding.pbDefense, it.defense, it.maxDefense)
                updatePb(binding.pbSpeed, it.speed, it.maxSpeed)
            })
        }
    }

    private fun updatePb(pb: NumberProgressBar, process: Int, maxProcess: Int) {
        pb.max  = maxProcess
        val animatorHp = ValueAnimator.ofInt(process)
        animatorHp.addUpdateListener {
            pb.progress = it.animatedValue as Int
        }
        animatorHp.start()
    }
}