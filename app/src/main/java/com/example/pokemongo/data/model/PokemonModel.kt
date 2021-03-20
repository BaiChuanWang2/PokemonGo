package com.example.pokemongo.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class PokemonModel(@PrimaryKey(autoGenerate = true) var id: Int = 0,
                        var name: String,
                        var url: String,
                        var nextPage: Int = 1) : Parcelable
