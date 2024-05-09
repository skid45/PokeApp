package com.skid.pokemon_list.data.model

import com.google.gson.annotations.SerializedName

internal data class OtherSpritesDto(
    @SerializedName("official-artwork") val officialArtworkSprites: OfficialArtworkSpritesDto,
)