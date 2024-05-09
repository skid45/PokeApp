package com.skid.pokemon_details.data

import com.skid.pokemon_details.data.model.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PokemonDetailsService {

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): PokemonDto
}