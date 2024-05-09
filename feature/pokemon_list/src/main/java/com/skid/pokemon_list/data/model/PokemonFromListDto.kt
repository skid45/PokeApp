package com.skid.pokemon_list.data.model

import com.google.gson.annotations.SerializedName

internal data class PokemonFromListDto(
    @SerializedName("name") val name: String,
)
