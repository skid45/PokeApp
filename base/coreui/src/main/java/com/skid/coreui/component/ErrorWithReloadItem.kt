package com.skid.coreui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.skid.coreui.R
import com.skid.coreui.theme.Dimens
import com.skid.coreui.theme.PokeAppTheme

@Composable
fun ErrorWithReloadItem(
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
            painter = painterResource(R.drawable.ic_reload),
            iconTint = MaterialTheme.colorScheme.primary,
            onClick = onReloadClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorItemPreview() {
    PokeAppTheme {
        ErrorWithReloadItem("Error") {}
    }
}