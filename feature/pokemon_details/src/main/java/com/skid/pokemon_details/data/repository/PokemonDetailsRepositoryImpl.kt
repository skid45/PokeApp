package com.skid.pokemon_details.data.repository

import com.skid.pokemon_details.data.PokemonDetailsService
import com.skid.pokemon_details.data.mapper.PokemonDataToDomainMapper
import com.skid.pokemon_details.domain.model.Pokemon
import com.skid.pokemon_details.domain.repository.PokemonDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PokemonDetailsRepositoryImpl
@Inject constructor(
    private val pokemonDetailsService: PokemonDetailsService,
    private val pokemonDataToDomainMapper: PokemonDataToDomainMapper,
) : PokemonDetailsRepository {

    override suspend fun getPokemon(name: String): Result<Pokemon> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = pokemonDetailsService.getPokemon(name)
            val pokemon = pokemonDataToDomainMapper(response)
            Result.success(pokemon)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}