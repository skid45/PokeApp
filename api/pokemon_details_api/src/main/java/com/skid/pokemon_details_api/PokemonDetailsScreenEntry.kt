package com.skid.pokemon_details_api

import com.skid.core.navigation.ScreenEntry


private const val POKEMON_DETAILS = "pokemon_details"

abstract class PokemonDetailsScreenEntry : ScreenEntry {

    val pokemonDetailsScreenModelKey = "pokemon_details_screen_model"

    final override val route: String = POKEMON_DETAILS
}