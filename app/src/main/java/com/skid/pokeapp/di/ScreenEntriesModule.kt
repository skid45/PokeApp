package com.skid.pokeapp.di

import com.skid.pokemon_details.di.PokemonDetailsScreenEntryModule
import com.skid.pokemon_list.di.PokemonListScreenEntryModule
import dagger.Module

@Module(
    includes = [
        PokemonListScreenEntryModule::class,
        PokemonDetailsScreenEntryModule::class,
    ]
)
internal interface ScreenEntriesModule