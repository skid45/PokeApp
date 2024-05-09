package com.skid.pokemon_details.domain.repository

import com.skid.pokemon_details.domain.model.Pokemon

internal interface PokemonDetailsRepository {

    suspend fun getPokemon(name: String): Result<Pokemon>
}