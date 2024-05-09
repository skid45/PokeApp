package com.skid.pokemon_details.data.model

import com.google.gson.annotations.SerializedName

internal data class TypeListItemDto(
    @SerializedName("type") val type: TypeDto,
)
