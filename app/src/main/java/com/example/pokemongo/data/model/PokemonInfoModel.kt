package com.example.pokemongo.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class PokemonInfoModel(@PrimaryKey(autoGenerate = true) var id: Int = 0,
                            var front_default: String,
                            var front_shiny: String,
                            var back_default: String,
                            var back_shiny: String,
                            var name: String) : Parcelable
