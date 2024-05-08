package com.skid.pokemon_details.api

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.skid.core.app.LocalApplicationProvider
import com.skid.core.di.injectedViewModel
import com.skid.core.navigation.di.ScreenEntriesMap
import com.skid.pokemon_details.di.PokemonDetailsComponent
import com.skid.pokemon_details_api.PokemonDetailsScreenEntry
import com.skid.pokemon_details_api.PokemonDetailsScreenModel
import com.skid.utils.customGetParcelable
import javax.inject.Inject

class PokemonDetailsScreenEntryImpl
@Inject constructor() : PokemonDetailsScreenEntry() {

    @Composable
    override fun Composable(
        navController: NavController,
        screens: ScreenEntriesMap,
        backStackEntry: NavBackStackEntry,
    ) {
        val screenModel = backStackEntry
            .arguments
            ?.customGetParcelable<PokemonDetailsScreenModel>(pokemonDetailsScreenModelKey)
            ?.let { model ->

                val applicationProvider = LocalApplicationProvider.current
                val viewModel = injectedViewModel {
                    PokemonDetailsComponent.init(
                        applicationProvider = applicationProvider,
                        model = model,
                    ).viewModel
                }
            }
    }
}