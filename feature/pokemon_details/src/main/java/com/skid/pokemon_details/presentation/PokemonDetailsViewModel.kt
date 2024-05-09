package com.skid.pokemon_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skid.core.resources.AppResources
import com.skid.pokemon_details.R
import com.skid.pokemon_details.domain.repository.PokemonDetailsRepository
import com.skid.pokemon_details_api.PokemonDetailsScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PokemonDetailsViewModel
@Inject constructor(
    private val model: PokemonDetailsScreenModel,
    private val resources: AppResources,
    private val pokemonDetailsRepository: PokemonDetailsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<PokemonDetailsState>(
        PokemonDetailsState.Loading(
            name = model.name,
            imageUrl = model.imageUrl,
        )
    )

    val state = _state.asStateFlow()

    init {
        onEvent(PokemonDetailsEvent.Load)
    }

    fun onEvent(event: PokemonDetailsEvent) {
        when (event) {
            PokemonDetailsEvent.Load -> loadPokemonDetails()
        }
    }

    private fun loadPokemonDetails() {
        _state.update { state ->
            PokemonDetailsState.Loading(
                name = state.name,
                imageUrl = state.imageUrl
            )
        }
        viewModelScope.launch {
            pokemonDetailsRepository
                .getPokemon(model.name.lowercase())
                .getOrNull()
                ?.let { pokemon ->
                    _state.update { state ->
                        PokemonDetailsState.Content(
                            name = state.name,
                            imageUrl = state.imageUrl,
                            height = pokemon.height,
                            weight = pokemon.weight,
                            types = pokemon.types,
                            abilities = pokemon.abilities
                        )
                    }
                }
                ?: _state.update { state ->
                    PokemonDetailsState.Error(
                        name = state.name,
                        imageUrl = state.imageUrl,
                        error = resources.getString(R.string.error_can_t_load_detailed_information)
                    )
                }
        }
    }
}