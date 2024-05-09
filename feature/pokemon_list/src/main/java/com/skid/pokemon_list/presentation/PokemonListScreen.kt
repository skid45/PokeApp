@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.skid.pokemon_list.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.skid.core.domain.PokemonType
import com.skid.core.navigation.di.ScreenEntriesMap
import com.skid.core.navigation.di.getScreenEntry
import com.skid.core.navigation.utils.navigate
import com.skid.coreui.component.ErrorWithReloadItem
import com.skid.coreui.component.Toolbar
import com.skid.coreui.theme.Dimens
import com.skid.coreui.theme.PokeAppTheme
import com.skid.coreui.utils.imageResId
import com.skid.pokemon_details_api.PokemonDetailsScreenEntry
import com.skid.pokemon_details_api.PokemonDetailsScreenModel
import com.skid.pokemon_list.R
import com.skid.pokemon_list.domain.model.Pokemon
import kotlinx.coroutines.flow.flowOf
import com.skid.coreui.R as coreUiR

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun PokemonListScreen(
    navController: NavController,
    screenEntriesMap: ScreenEntriesMap,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: LazyPagingItems<Pokemon>,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(title = stringResource(R.string.pokemons))
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            when (state.loadState.refresh) {
                LoadState.Loading -> CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                )

                is LoadState.Error -> ErrorWithReloadItem(
                    modifier = Modifier.fillMaxSize(),
                    errorText = stringResource(R.string.error_please_check_the_connection),
                    onReloadClick = state::refresh
                )

                else -> PokemonList(
                    state = state,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope,
                    onItemClick = { pokemon ->
                        val pokemonDetailsScreenEntry = screenEntriesMap.getScreenEntry<PokemonDetailsScreenEntry>()
                        navController.navigate(
                            route = pokemonDetailsScreenEntry.route,
                            args = bundleOf(
                                pokemonDetailsScreenEntry.pokemonDetailsScreenModelKey
                                        to PokemonDetailsScreenModel(
                                    name = pokemon.name,
                                    imageUrl = pokemon.imageUrl
                                )
                            )
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PokemonList(
    state: LazyPagingItems<Pokemon>,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (Pokemon) -> Unit,
) {
    LazyColumn {
        items(count = state.itemCount) { index ->
            state[index]?.let { pokemon ->
                PokemonItem(
                    modifier = Modifier.animateItemPlacement(),
                    pokemon = pokemon,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope,
                    onClick = onItemClick
                )
            }
        }

        when (state.loadState.append) {
            LoadState.Loading -> {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth()
                            .padding(vertical = Dimens.Small)
                    )
                }
            }

            is LoadState.Error -> {
                item {
                    ErrorWithReloadItem(
                        errorText = stringResource(R.string.an_error_occurred_while_loading_a_new_page),
                        onReloadClick = state::retry
                    )
                }
            }

            is LoadState.NotLoading -> {}
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PokemonItem(
    pokemon: Pokemon,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    showDivider: Boolean = true,
    onClick: (Pokemon) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(pokemon) }
            .padding(start = Dimens.Small, end = Dimens.Small, top = Dimens.Small)
    ) {
        Row {
            with(sharedTransitionScope) {
                AsyncImage(
                    modifier = Modifier
                        .size(175.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = pokemon.imageUrl),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ -> tween(durationMillis = 600) }
                        ),
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    placeholder = painterResource(coreUiR.drawable.pokemon_placeholder),
                    error = painterResource(coreUiR.drawable.pokemon_placeholder),
                )
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(Dimens.Medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(Dimens.Large))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimens.XSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimens.XSmall),
                ) {
                    pokemon.types.forEach { imageResId ->
                        Image(
                            modifier = Modifier.defaultMinSize(minHeight = Dimens.Medium),
                            painter = painterResource(imageResId),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(Dimens.Small))
        if (showDivider) HorizontalDivider()
    }
}

@Suppress("UNUSED_EXPRESSION")
@Preview(showBackground = true)
@Composable
private fun PokemonListScreenPreview() {
    PokeAppTheme {
        SharedTransitionLayout {
            AnimatedContent(null, label = "") {
                it
                PokemonListScreen(
                    navController = rememberNavController(),
                    screenEntriesMap = mapOf(),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@AnimatedContent,
                    state = flowOf(
                        PagingData.from(
                            data = List(10) {
                                Pokemon(
                                    id = it,
                                    name = "Mew$it",
                                    imageUrl = "",
                                    types = PokemonType.entries
                                        .take(it + 1)
                                        .map(PokemonType::imageResId)
                                )
                            },
                            sourceLoadStates = LoadStates(
                                refresh = LoadState.NotLoading(false),
                                prepend = LoadState.NotLoading(false),
                                append = LoadState.NotLoading(false)
                            )
                        )
                    ).collectAsLazyPagingItems(),
                )
            }
        }
    }
}

@Suppress("UNUSED_EXPRESSION")
@Preview(showBackground = true)
@Composable
private fun PokemonItemPreview() {
    PokeAppTheme {
        SharedTransitionLayout {
            AnimatedContent(null, label = "") {
                it
                PokemonItem(
                    pokemon = Pokemon(
                        id = 1,
                        name = "Mewtwo",
                        imageUrl = "",
                        types = listOf(
                            PokemonType.DRAGON.imageResId,
                            PokemonType.FLYING.imageResId,
                            PokemonType.FLYING.imageResId,
                            PokemonType.FLYING.imageResId,
                            PokemonType.UNKNOWN.imageResId,
                            PokemonType.STEEL.imageResId,
                        )
                    ),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@AnimatedContent
                ) {

                }
            }
        }
    }
}