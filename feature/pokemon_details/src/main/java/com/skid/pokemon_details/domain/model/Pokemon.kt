package com.skid.pokemon_details.domain.model

internal data class Pokemon(
    val id: Int,
    val name: String,
    val height: String,
    val weight: String,
    val imageUrl: String,
    val types: List<Int>,
    val abilities: List<String>,
)
