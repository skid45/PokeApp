package com.skid.pokemon_details.presentation

internal sealed class PokemonDetailsEvent {

    data object Load : PokemonDetailsEvent()
}