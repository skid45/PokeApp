package com.skid.core.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.skid.core.navigation.di.ScreenEntriesMap

@OptIn(ExperimentalSharedTransitionApi::class)
interface ScreenEntry {

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    val deepLinks: List<NavDeepLink>
        get() = emptyList()

    fun NavGraphBuilder.composable(
        navController: NavHostController,
        screens: ScreenEntriesMap,
        sharedTransitionScope: SharedTransitionScope,
    ) {
        composable(
            route = this@ScreenEntry.route,
            arguments = arguments,
            deepLinks = deepLinks
        ) { backStackEntry ->
            Composable(
                navController = navController,
                screens = screens,
                backStackEntry = backStackEntry,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this
            )
        }
    }

    @Composable
    fun Composable(
        navController: NavController,
        screens: ScreenEntriesMap,
        backStackEntry: NavBackStackEntry,
        sharedTransitionScope: SharedTransitionScope,
        animatedVisibilityScope: AnimatedVisibilityScope,
    )
}