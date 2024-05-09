package com.skid.pokeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.skid.core.app.LocalApplicationProvider
import com.skid.core.navigation.di.getScreenEntry
import com.skid.coreui.theme.PokeAppTheme
import com.skid.pokeapp.app.PokeApp
import com.skid.pokemon_list_api.PokemonListScreenEntry

internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CompositionLocalProvider(
                        LocalApplicationProvider provides (application as PokeApp).getApplicationProvider()
                    ) {
                        val screenEntriesMap = LocalApplicationProvider.current.screenEntriesMap
                        Navigation(
                            screenEntries = screenEntriesMap,
                            startDestination = screenEntriesMap.getScreenEntry<PokemonListScreenEntry>().route
                        )
                    }
                }
            }
        }
    }
}