package com.skid.coreui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.skid.coreui.theme.Dimens

@Composable
fun IconButton(
    painter: Painter,
    modifier: Modifier = Modifier,
    iconTint: Color = Color.Unspecified,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.padding(Dimens.Small),
            painter = painter,
            contentDescription = contentDescription,
            tint = iconTint
        )
    }
}