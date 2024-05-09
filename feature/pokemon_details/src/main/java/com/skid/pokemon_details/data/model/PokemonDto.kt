package com.skid.pokemon_details.data.model

import com.google.gson.annotations.SerializedName

internal data class PokemonDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("sprites") val sprites: SpritesDto,
    @SerializedName("types") val types: List<TypeListItemDto>,
    @SerializedName("abilities") val abilities: List<AbilitiesListItemDto>,
)