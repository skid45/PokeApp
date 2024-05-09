package com.skid.pokemon_details.presentation

import androidx.compose.runtime.Immutable
import com.skid.utils.Empty


@Immutable
internal sealed class PokemonDetailsState(
    open val name: String,
    open val imageUrl: String,
) {

    data class Loading(
        override val name: String,
        override val imageUrl: String,
    ) : PokemonDetailsState(name, imageUrl)

    data class Content(
        override val name: String,
        override val imageUrl: String,
        val height: String = String.Empty,
        val weight: String = String.Empty,
        val types: List<Int> = emptyList(),
        val abilities: List<String> = emptyList(),
    ) : PokemonDetailsState(name, imageUrl)

    data class Error(
        override val name: String,
        override val imageUrl: String,
        val error: String,
    ) : PokemonDetailsState(name, imageUrl)
}