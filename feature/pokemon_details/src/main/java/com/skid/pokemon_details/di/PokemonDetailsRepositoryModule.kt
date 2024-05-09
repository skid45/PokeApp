package com.skid.pokemon_details.di

import com.skid.pokemon_details.data.PokemonDetailsService
import com.skid.pokemon_details.data.repository.PokemonDetailsRepositoryImpl
import com.skid.pokemon_details.domain.repository.PokemonDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal interface PokemonDetailsRepositoryModule {

    companion object {

        @Provides
        fun providePokemonDetailsService(retrofit: Retrofit): PokemonDetailsService {
            return retrofit.create(PokemonDetailsService::class.java)
        }
    }

    @Binds
    fun bindPokemonDetailsRepository(impl: PokemonDetailsRepositoryImpl): PokemonDetailsRepository
}