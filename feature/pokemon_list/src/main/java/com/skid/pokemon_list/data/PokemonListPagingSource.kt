package com.skid.pokemon_list.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skid.pokemon_list.data.mapper.PokemonDataToDomainMapper
import com.skid.pokemon_list.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PokemonListPagingSource
@Inject constructor(
    private val pokemonListService: PokemonListService,
    private val pokemonDataToDomainMapper: PokemonDataToDomainMapper,
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> = withContext(Dispatchers.IO) {
        val key = (params.key ?: 0)
        val offset = params.loadSize * key
        return@withContext try {
            val response = pokemonListService.getPokemonList(
                offset = offset,
                limit = params.loadSize
            )
            val pokemonList = response.pokemonList.map { pokemon ->
                val fullPokemon = pokemonListService.getPokemonByName(pokemon.name)
                pokemonDataToDomainMapper(fullPokemon)
            }
            LoadResult.Page(
                data = pokemonList,
                prevKey = null,
                nextKey = if (pokemonList.size < params.loadSize) null else key + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }
}