package com.skid.pokemon_details.api

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.skid.core.app.LocalApplicationProvider
import com.skid.core.di.injectedViewModel
import com.skid.core.navigation.di.ScreenEntriesMap
import com.skid.pokemon_details.di.PokemonDetailsComponent
import com.skid.pokemon_details.presentation.PokemonDetailsScreen
import com.skid.pokemon_details_api.PokemonDetailsScreenEntry
import com.skid.pokemon_details_api.PokemonDetailsScreenModel
import com.skid.utils.customGetParcelable
import javax.inject.Inject

class PokemonDetailsScreenEntryImpl
@Inject constructor() : PokemonDetailsScreenEntry() {

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    override fun Composable(
        navController: NavController,
        screens: ScreenEntriesMap,
        backStackEntry: NavBackStackEntry,
        sharedTransitionScope: SharedTransitionScope,
        animatedVisibilityScope: AnimatedVisibilityScope,
    ) {
        backStackEntry.arguments
            ?.customGetParcelable<PokemonDetailsScreenModel>(pokemonDetailsScreenModelKey)
            ?.let { model ->
                val applicationProvider = LocalApplicationProvider.current
                val viewModel = injectedViewModel {
                    PokemonDetailsComponent.init(
                        applicationProvider = applicationProvider,
                        model = model,
                    ).viewModel
                }
                val state by viewModel.state.collectAsStateWithLifecycle()
                PokemonDetailsScreen(
                    navController = navController,
                    state = state,
                    onEvent = viewModel::onEvent,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
    }
}