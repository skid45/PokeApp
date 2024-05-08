package com.skid.pokemon_list.di

import com.skid.core.navigation.ScreenEntry
import com.skid.core.navigation.di.ScreenEntryKey
import com.skid.pokemon_list.api.PokemonListScreenEntryImpl
import com.skid.pokemon_list_api.PokemonListScreenEntry
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface PokemonListScreenEntryModule {

    @Singleton
    @Binds
    fun bindPokemonListScreenEntry(impl: PokemonListScreenEntryImpl): PokemonListScreenEntry

    @Singleton
    @Binds
    @IntoMap
    @ScreenEntryKey(PokemonListScreenEntry::class)
    fun bindPokemonListScreenEntryToMap(screenEntry: PokemonListScreenEntry): ScreenEntry
}