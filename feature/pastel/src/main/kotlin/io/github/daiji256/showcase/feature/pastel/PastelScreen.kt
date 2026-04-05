package io.github.daiji256.showcase.feature.pastel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Slider
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator

@Composable
internal fun PastelScreen() {
    val navigator = LocalNavigator.current
    PastelScreen(
        onNavigateUpClick = navigator::navigateUp,
    )
}

@Composable
private fun PastelScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_pastel_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(onClick = {}) {
                Text(text = stringResource(id = R.string.feature_pastel_button))
            }
            ElevatedButton(onClick = {}) {
                Text(text = stringResource(id = R.string.feature_pastel_button))
            }
            OutlinedButton(onClick = {}) {
                Text(text = stringResource(id = R.string.feature_pastel_button))
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Card(modifier = Modifier.size(48.dp)) {}
            ElevatedCard(modifier = Modifier.size(48.dp)) {}
            OutlinedCard(modifier = Modifier.size(48.dp)) {}
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TextField(
                value = stringResource(id = R.string.feature_pastel_textfield),
                label = { Text(text = stringResource(id = R.string.feature_pastel_textfield)) },
                isError = false,
                onValueChange = {},
                modifier = Modifier.width(128.dp),
            )
            TextField(
                value = stringResource(id = R.string.feature_pastel_textfield),
                label = { Text(text = stringResource(id = R.string.feature_pastel_textfield)) },
                isError = true,
                onValueChange = {},
                modifier = Modifier.width(128.dp),
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Checkbox(checked = false, onCheckedChange = null)
            Checkbox(checked = true, onCheckedChange = null)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Switch(checked = false, onCheckedChange = null)
            Switch(checked = true, onCheckedChange = null)
        }
        FloatingActionButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_fab),
                contentDescription = null,
            )
        }
        Slider(value = 0.3f, onValueChange = {})
        Snackbar(
            action = {
                TextButton(
                    onClick = {},
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = SnackbarDefaults.actionColor,
                    ),
                ) {
                    Text(text = stringResource(id = R.string.feature_pastel_snackbar_action))
                }
            },
        ) {
            Text(text = stringResource(id = R.string.feature_pastel_snackbar))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PastelScreenPreview() {
    ShowcaseTheme {
        PastelScreen(
            onNavigateUpClick = {},
        )
    }
}
