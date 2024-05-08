package com.skid.pokemon_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.skid.pokemon_list.domain.repository.PokemonListRepository
import javax.inject.Inject

class PokemonListViewModel
@Inject constructor(
    pokemonListRepository: PokemonListRepository,
) : ViewModel() {

    val state = pokemonListRepository
        .getPokemonListPagingSource()
        .cachedIn(viewModelScope)
}