package com.skid.coreui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import com.skid.coreui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: Painter? = null,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    onNavigationIconClick: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier
            .shadow(elevation = Dimens.Small),
        title = {
            Text(
                modifier = Modifier.padding(start = Dimens.Small),
                text = title,
                style = textStyle
            )
        },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(
                    modifier = Modifier.padding(Dimens.Medium),
                    painter = navigationIcon,
                    onClick = { onNavigationIconClick?.invoke() }
                )
            }
        }
    )
}