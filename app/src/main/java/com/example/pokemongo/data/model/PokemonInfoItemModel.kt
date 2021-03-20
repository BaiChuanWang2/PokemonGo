package com.example.pokemongo.data.model

import kotlin.random.Random

data class PokemonInfoItemModel(val hp: Int = Random.nextInt(MAX_HP),
                                val attack: Int = Random.nextInt(MAX_ATTACK),
                                val defense: Int = Random.nextInt(MAX_DEFENSE),
                                val speed: Int = Random.nextInt(MAX_SPEED),
                                val maxHp: Int = MAX_HP,
                                val maxAttack: Int = MAX_ATTACK,
                                val maxDefense: Int = MAX_DEFENSE,
                                val maxSpeed: Int = MAX_SPEED,
                                val imgModels: ArrayList<ImgModel>) {
    companion object {
        const val MAX_HP = 2000
        const val MAX_ATTACK = 300
        const val MAX_DEFENSE = 300
        const val MAX_SPEED = 100
    }
    data class ImgModel(val url: String)
}
