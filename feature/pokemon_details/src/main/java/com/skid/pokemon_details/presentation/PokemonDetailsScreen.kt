@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.skid.pokemon_details.presentation

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.skid.core.domain.PokemonType
import com.skid.coreui.component.ErrorWithReloadItem
import com.skid.coreui.component.Toolbar
import com.skid.coreui.theme.Dimens
import com.skid.coreui.theme.PokeAppTheme
import com.skid.coreui.utils.imageResId
import com.skid.pokemon_details.R
import com.skid.coreui.R as coreUiR

@Composable
internal fun PokemonDetailsScreen(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: PokemonDetailsState,
    onEvent: (PokemonDetailsEvent) -> Unit,
) {
    val orientation = LocalConfiguration.current.orientation

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Toolbar(
                title = state.name,
                navigationIcon = painterResource(coreUiR.drawable.ic_arrow_back),
                onNavigationIconClick = navController::popBackStack
            )
        }
    ) { padding ->
        val modifier = Modifier.padding(padding)
        if (orientation == ORIENTATION_PORTRAIT) {
            Column(modifier = modifier) {
                PokemonDetailsScreenContent(
                    state = state,
                    orientation = orientation,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope,
                    onReloadClick = { onEvent(PokemonDetailsEvent.Load) },
                )
            }
        } else {
            Row(modifier = modifier) {
                PokemonDetailsScreenContent(
                    state = state,
                    orientation = orientation,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope,
                    onReloadClick = { onEvent(PokemonDetailsEvent.Load) },
                )
            }
        }
    }
}

@Composable
private fun PokemonDetailsScreenContent(
    state: PokemonDetailsState,
    orientation: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onReloadClick: () -> Unit,
) {
    with(sharedTransitionScope) {
        AsyncImage(
            modifier = Modifier
                .then(
                    if (orientation == ORIENTATION_PORTRAIT) {
                        Modifier.fillMaxWidth()
                    } else {
                        Modifier.fillMaxHeight()
                    }
                )
                .padding(Dimens.Small)
                .sharedElement(
                    state = rememberSharedContentState(key = state.imageUrl),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ -> tween(durationMillis = 600) }
                ),
            model = state.imageUrl,
            contentDescription = state.name,
            placeholder = painterResource(coreUiR.drawable.pokemon_placeholder),
            error = painterResource(coreUiR.drawable.pokemon_placeholder)
        )
    }

    when (state) {
        is PokemonDetailsState.Loading -> CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )

        is PokemonDetailsState.Content -> PokemonDetailsItem(state)

        is PokemonDetailsState.Error -> ErrorWithReloadItem(
            modifier = Modifier.fillMaxSize(),
            errorText = state.error,
            onReloadClick = onReloadClick
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PokemonDetailsItem(
    pokemonDetails: PokemonDetailsState.Content,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(Dimens.Medium)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.height, pokemonDetails.height))
            Text(text = stringResource(id = R.string.weight, pokemonDetails.weight))
        }
        HorizontalDivider(Modifier.padding(vertical = Dimens.Small))
        Text(text = stringResource(R.string.types))
        FlowRow(
            Modifier.padding(Dimens.Small),
            horizontalArrangement = Arrangement.spacedBy(Dimens.XSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.XSmall),
        ) {
            pokemonDetails.types.forEach { imageResId ->
                Image(
                    modifier = Modifier.defaultMinSize(minHeight = Dimens.Medium),
                    painter = painterResource(imageResId),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }
        }
        HorizontalDivider(Modifier.padding(vertical = Dimens.Small))
        Text(text = "Abilities")
        Column(modifier = Modifier.padding(Dimens.Small)) {
            pokemonDetails.abilities.forEachIndexed { index, ability ->
                Text(text = stringResource(R.string.ability_item_mask, index + 1, ability))
            }
        }
    }
}

@Suppress("UNUSED_EXPRESSION")
@Preview
@Composable
private fun PokemonDetailsScreenLoadingPreview() {
    PokeAppTheme {
        SharedTransitionLayout {
            AnimatedContent(null, label = "") {
                it
                PokemonDetailsScreen(
                    navController = rememberNavController(),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@AnimatedContent,
                    state = PokemonDetailsState.Loading(name = "Mew", imageUrl = "")
                ) {}
            }
        }
    }
}

@Suppress("UNUSED_EXPRESSION")
@Preview
@Composable
private fun PokemonDetailsScreenContentPreview() {
    PokeAppTheme {
        SharedTransitionLayout {
            AnimatedContent(null, label = "") {
                it
                PokemonDetailsScreen(
                    navController = rememberNavController(),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@AnimatedContent,
                    state = PokemonDetailsState.Content(
                        name = "Mew",
                        imageUrl = "",
                        height = "6 kg",
                        weight = "0.7 m",
                        types = PokemonType.entries.map(PokemonType::imageResId),
                        abilities = List(15) { "Shock" }
                    )
                ) {}
            }
        }
    }
}

@Suppress("UNUSED_EXPRESSION")
@Preview
@Composable
private fun PokemonDetailsScreenErrorPreview() {
    PokeAppTheme {
        SharedTransitionLayout {
            AnimatedContent(null, label = "") {
                it
                PokemonDetailsScreen(
                    navController = rememberNavController(),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@AnimatedContent,
                    state = PokemonDetailsState.Error(
                        name = "Pikachu",
                        imageUrl = "",
                        error = "Error"
                    )
                ) {}
            }
        }
    }
}