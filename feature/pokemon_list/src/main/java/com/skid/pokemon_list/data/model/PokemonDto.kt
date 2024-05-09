package com.skid.pokemon_list.data.model

import com.google.gson.annotations.SerializedName

internal data class PokemonDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: SpritesDto,
    @SerializedName("types") val types: List<TypeListItemDto>,
)