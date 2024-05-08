package com.skid.pokemon_list.domain.repository

import androidx.paging.PagingData
import com.skid.pokemon_list.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonListRepository {

    fun getPokemonListPagingSource(): Flow<PagingData<Pokemon>>
}