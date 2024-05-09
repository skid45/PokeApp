package com.skid.pokemon_details.di

import com.skid.core.app.ApplicationProvider
import com.skid.pokemon_details.presentation.PokemonDetailsViewModel
import com.skid.pokemon_details_api.PokemonDetailsScreenModel
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [ApplicationProvider::class],
    modules = [PokemonDetailsRepositoryModule::class]
)
internal interface PokemonDetailsComponent {

    companion object {

        fun init(
            applicationProvider: ApplicationProvider,
            model: PokemonDetailsScreenModel,
        ) = DaggerPokemonDetailsComponent.factory()
            .create(
                applicationProvider = applicationProvider,
                model = model
            )
    }

    val viewModel: PokemonDetailsViewModel

    @Component.Factory
    interface Factory {

        fun create(
            applicationProvider: ApplicationProvider,
            @BindsInstance model: PokemonDetailsScreenModel,
        ): PokemonDetailsComponent
    }
}