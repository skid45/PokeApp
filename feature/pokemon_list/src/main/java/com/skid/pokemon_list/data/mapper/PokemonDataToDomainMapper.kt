package com.skid.pokemon_list.data.mapper

import com.skid.core.domain.PokemonType
import com.skid.coreui.utils.imageResId
import com.skid.pokemon_list.data.model.PokemonDto
import com.skid.pokemon_list.domain.model.Pokemon
import java.util.Locale
import javax.inject.Inject

class PokemonDataToDomainMapper
@Inject constructor() {

    operator fun invoke(dto: PokemonDto) = Pokemon(
        id = dto.id,
        name = dto.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        },
        imageUrl = dto.sprites.otherSprites.officialArtworkSprites.imageUrl,
        types = dto.types.map { typeListItemDto ->
            val typeName = typeListItemDto.type.name.uppercase()
            try {
                PokemonType.valueOf(typeName).imageResId
            } catch (e: IllegalArgumentException) {
                PokemonType.UNKNOWN.imageResId
            }
        }
    )
}