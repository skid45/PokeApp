package com.skid.pokemon_list.data.model

import com.google.gson.annotations.SerializedName

data class TypeListItemDto(
    @SerializedName("type") val type: TypeDto,
)
