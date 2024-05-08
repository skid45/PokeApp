package com.skid.pokemon_list.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListResponseDto(
    @SerializedName("results") val pokemonList: List<PokemonFromListDto>,
)
