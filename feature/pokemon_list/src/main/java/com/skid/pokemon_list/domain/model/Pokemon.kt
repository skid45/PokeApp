package com.skid.pokemon_list.domain.model

internal data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<Int>,
)
