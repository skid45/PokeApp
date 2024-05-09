package com.skid.pokemon_list.api

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.skid.core.app.LocalApplicationProvider
import com.skid.core.di.injectedViewModel
import com.skid.core.navigation.di.ScreenEntriesMap
import com.skid.pokemon_list.di.PokemonListComponent
import com.skid.pokemon_list.presentation.PokemonListScreen
import com.skid.pokemon_list_api.PokemonListScreenEntry
import javax.inject.Inject

class PokemonListScreenEntryImpl
@Inject constructor() : PokemonListScreenEntry() {

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    override fun Composable(
        navController: NavController,
        screens: ScreenEntriesMap,
        backStackEntry: NavBackStackEntry,
        sharedTransitionScope: SharedTransitionScope,
        animatedVisibilityScope: AnimatedVisibilityScope,
    ) {
        val applicationProvider = LocalApplicationProvider.current
        val viewModel = injectedViewModel {
            PokemonListComponent
                .init(applicationProvider)
                .viewModel
        }
        val state = viewModel.state.collectAsLazyPagingItems()
        PokemonListScreen(
            navController = navController,
            screenEntriesMap = screens,
            state = state,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}