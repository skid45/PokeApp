package com.skid.pokemon_list.data.model

import com.google.gson.annotations.SerializedName

data class SpritesDto(
    @SerializedName("other") val otherSprites: OtherSpritesDto,
)