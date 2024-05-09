package com.skid.pokemon_details.data.mapper

import com.skid.core.domain.PokemonType
import com.skid.coreui.utils.imageResId
import com.skid.pokemon_details.data.model.PokemonDto
import com.skid.pokemon_details.domain.model.Pokemon
import com.skid.utils.capitalize
import com.skid.utils.toString
import javax.inject.Inject

internal class PokemonDataToDomainMapper
@Inject constructor() {

    operator fun invoke(dto: PokemonDto) = Pokemon(
        id = dto.id,
        name = dto.name.capitalize(),
        height = (dto.height.toDouble() / 10).toString(removeTheZeroFractionalPart = true),
        weight = (dto.weight.toDouble() / 10).toString(removeTheZeroFractionalPart = true),
        imageUrl = dto.sprites.otherSprites.officialArtworkSprites.imageUrl,
        types = dto.types.map { typeListItemDto ->
            val typeName = typeListItemDto.type.name.uppercase()
            try {
                PokemonType.valueOf(typeName).imageResId
            } catch (e: IllegalArgumentException) {
                PokemonType.UNKNOWN.imageResId
            }
        },
        abilities = dto.abilities.map { abilitiesListItemDto ->
            abilitiesListItemDto.ability.name.capitalize()
        }
    )
}