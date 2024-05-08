package com.skid.pokemon_details_api

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.skid.core.navigation.ScreenEntry


private const val POKEMON_DETAILS = "pokemon_details"

abstract class PokemonDetailsScreenEntry : ScreenEntry {

    protected val pokemonDetailsScreenModelKey = "pokemon_details_screen_model"

    final override val route: String = "$POKEMON_DETAILS/{${pokemonDetailsScreenModelKey}}"

    final override val arguments: List<NamedNavArgument> = listOf(
        navArgument(pokemonDetailsScreenModelKey) {
            type = NavType.ParcelableType(PokemonDetailsScreenModel::class.java)
        }
    )

    fun getDestination(model: PokemonDetailsScreenModel): String {
        return "$POKEMON_DETAILS/${model}"
    }
}