package com.skid.pokemon_details.data.model

import com.google.gson.annotations.SerializedName

internal data class OfficialArtworkSpritesDto(
    @SerializedName("front_default") val imageUrl: String,
)