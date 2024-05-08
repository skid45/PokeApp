package com.skid.pokemon_list.presentation

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
import com.skid.coreui.component.IconButton
import com.skid.coreui.component.Toolbar
import com.skid.coreui.theme.Dimens
import com.skid.coreui.theme.PokeAppTheme
import com.skid.coreui.utils.imageResId
import com.skid.pokemon_list.R
import com.skid.pokemon_list.domain.model.Pokemon
import kotlinx.coroutines.flow.flowOf
import com.skid.coreui.R as coreUiR

@Composable
fun PokemonListScreen(
    navController: NavController,
    screenEntriesMap: ScreenEntriesMap,
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

                is LoadState.Error -> ErrorItem(
                    modifier = Modifier.fillMaxSize(),
                    errorText = stringResource(R.string.error_please_check_the_connection),
                    onReloadClick = state::refresh
                )

                else -> PokemonList(
                    state = state,
                    onItemClick = {} // TODO (navigate on pokemon details screen)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonList(
    state: LazyPagingItems<Pokemon>,
    onItemClick: (Pokemon) -> Unit,
) {
    LazyColumn {
        items(count = state.itemCount) { index ->
            state[index]?.let { pokemon ->
                PokemonItem(
                    modifier = Modifier.animateItemPlacement(),
                    pokemon = pokemon,
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
                    ErrorItem(
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
fun PokemonItem(
    pokemon: Pokemon,
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
            AsyncImage(
                modifier = Modifier.size(175.dp),
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                placeholder = painterResource(coreUiR.drawable.pokemon_placeholder),
                error = painterResource(coreUiR.drawable.pokemon_placeholder),
            )
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

@Composable
fun ErrorItem(
    errorText: String,
    modifier: Modifier = Modifier,
    onReloadClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.Small),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorText,
            color = MaterialTheme.colorScheme.error
        )
        Text(
            text = stringResource(R.string.try_to_load_again),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(Dimens.Small))
        IconButton(
            painter = painterResource(coreUiR.drawable.ic_reload),
            iconTint = MaterialTheme.colorScheme.primary,
            onClick = onReloadClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonListScreenPreview() {
    PokeAppTheme {
        PokemonListScreen(
            navController = rememberNavController(),
            screenEntriesMap = mapOf(),
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

@Preview(showBackground = true)
@Composable
fun PokemonItemPreview() {
    PokeAppTheme {
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
            )
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorItemPreview() {
    PokeAppTheme {
        ErrorItem(stringResource(R.string.an_error_occurred_while_loading_a_new_page)) {}
    }
}
