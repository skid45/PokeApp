package com.skid.pokemon_details_api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailsScreenModel(
    val name: String,
    val imageUrl: String,
) : Parcelable
