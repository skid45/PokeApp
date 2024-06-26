package com.skid.pokeapp.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.skid.core.navigation.di.ScreenEntriesMap

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun Navigation(
    screenEntries: ScreenEntriesMap,
    startDestination: String,
) {
    val navController = rememberNavController()

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            screenEntries.values.forEach { screenEntry ->
                with(screenEntry) {
                    composable(
                        navController = navController,
                        screens = screenEntries,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                }
            }
        }
    }
}