package com.skid.pokemon_list.di

import com.skid.core.app.ApplicationProvider
import com.skid.pokemon_list.presentation.PokemonListViewModel
import dagger.Component

@Component(
    dependencies = [ApplicationProvider::class],
    modules = [PokemonListRepositoryModule::class],
)
internal interface PokemonListComponent {

    companion object {

        fun init(
            applicationProvider: ApplicationProvider,
        ) = DaggerPokemonListComponent.factory()
            .create(applicationProvider)
    }

    val viewModel: PokemonListViewModel

    @Component.Factory
    interface Factory {

        fun create(
            applicationProvider: ApplicationProvider,
        ): PokemonListComponent
    }
}