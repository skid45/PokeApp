package com.skid.pokemon_list.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.skid.pokemon_list.data.PokemonListPagingSource
import com.skid.pokemon_list.domain.model.Pokemon
import com.skid.pokemon_list.domain.repository.PokemonListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Provider

private const val PAGE_SIZE = 20

class PokemonListRepositoryImpl
@Inject constructor(
    private val pokemonListPagingSource: Provider<PokemonListPagingSource>,
) : PokemonListRepository {

    override fun getPokemonListPagingSource(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE
            ),
            initialKey = 0,
            pagingSourceFactory = pokemonListPagingSource::get
        ).flow
    }
}