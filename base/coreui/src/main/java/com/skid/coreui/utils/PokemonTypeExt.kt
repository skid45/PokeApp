package com.skid.coreui.utils

import androidx.annotation.DrawableRes
import com.skid.core.domain.PokemonType
import com.skid.coreui.R

@get:DrawableRes
val PokemonType.imageResId: Int
    get() = when (this) {
        PokemonType.NORMAL -> R.drawable.normal_type
        PokemonType.FIGHTING -> R.drawable.fighting_type
        PokemonType.FLYING -> R.drawable.flying_type
        PokemonType.POISON -> R.drawable.poison_type
        PokemonType.GROUND -> R.drawable.ground_type
        PokemonType.ROCK -> R.drawable.rock_type
        PokemonType.BUG -> R.drawable.bug_type
        PokemonType.GHOST -> R.drawable.ghost_type
        PokemonType.STEEL -> R.drawable.steel_type
        PokemonType.FIRE -> R.drawable.fire_type
        PokemonType.WATER -> R.drawable.water_type
        PokemonType.GRASS -> R.drawable.grass_type
        PokemonType.ELECTRIC -> R.drawable.electric_type
        PokemonType.PSYCHIC -> R.drawable.psychic_type
        PokemonType.ICE -> R.drawable.ice_type
        PokemonType.DRAGON -> R.drawable.dragon_type
        PokemonType.DARK -> R.drawable.dark_type
        PokemonType.FAIRY -> R.drawable.fairy_type
        PokemonType.STELLAR -> R.drawable.stellar_type
        PokemonType.UNKNOWN -> R.drawable.unknown_type
    }