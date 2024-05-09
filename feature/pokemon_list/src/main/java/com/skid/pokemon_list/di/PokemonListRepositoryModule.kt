package com.skid.pokemon_list.di

import com.skid.pokemon_list.data.PokemonListService
import com.skid.pokemon_list.data.repository.PokemonListRepositoryImpl
import com.skid.pokemon_list.domain.repository.PokemonListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal interface PokemonListRepositoryModule {

    companion object {

        @Provides
        fun providePokemonListService(retrofit: Retrofit): PokemonListService {
            return retrofit.create(PokemonListService::class.java)
        }
    }

    @Binds
    fun bindPokemonListRepository(impl: PokemonListRepositoryImpl): PokemonListRepository
}