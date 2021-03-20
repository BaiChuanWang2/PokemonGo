package com.example.pokemongo.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import com.example.pokemongo.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(LayoutInflater.from(this)).apply {
            binding = this
            setContentView(binding.root)
        }
    }
}