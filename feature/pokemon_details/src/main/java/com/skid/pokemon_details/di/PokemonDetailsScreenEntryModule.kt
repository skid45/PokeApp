package com.skid.pokemon_details.di

import com.skid.core.navigation.ScreenEntry
import com.skid.core.navigation.di.ScreenEntryKey
import com.skid.pokemon_details.api.PokemonDetailsScreenEntryImpl
import com.skid.pokemon_details_api.PokemonDetailsScreenEntry
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface PokemonDetailsScreenEntryModule {

    @Singleton
    @Binds
    fun bindPokemonDetailsScreenEntry(impl: PokemonDetailsScreenEntryImpl): PokemonDetailsScreenEntry

    @Singleton
    @Binds
    @IntoMap
    @ScreenEntryKey(PokemonDetailsScreenEntry::class)
    fun bindPokemonDetailsScreenEntryIntoMap(screenEntry: PokemonDetailsScreenEntry): ScreenEntry
}