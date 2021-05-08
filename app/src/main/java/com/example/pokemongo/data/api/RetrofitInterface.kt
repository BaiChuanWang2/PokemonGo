package com.example.pokemongo.data.api

import com.example.pokemongo.data.api.`in`.GetPokeMonResponse
import com.example.pokemongo.data.api.`in`.GetPokemonInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("pokemon")
    suspend fun getPokemon(@Query("limit") limit: Int, @Query("offset") offset: Int): GetPokeMonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name: String): GetPokemonInfoResponse
}