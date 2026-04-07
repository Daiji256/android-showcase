package io.github.daiji256.showcase.feature.pastel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Slider
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    PastelTheme {
        Document(
            title = stringResource(id = R.string.feature_pastel_title),
            onNavigateUpClick = onNavigateUpClick,
        ) {
            ColorPalette()
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
}

@Composable
private fun ColorPalette(modifier: Modifier = Modifier) {
    val cs = MaterialTheme.colorScheme
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .background(color = Color.Gray)
            .padding(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Column {
                ColorPaletteItem(color = cs.primary, onColor = cs.onPrimary)
                ColorPaletteItem(color = cs.onPrimary, onColor = cs.primary)
            }
            Column {
                ColorPaletteItem(color = cs.primaryContainer, onColor = cs.onPrimaryContainer)
                ColorPaletteItem(color = cs.onPrimaryContainer, onColor = cs.primaryContainer)
            }
            Column {
                ColorPaletteItem(color = cs.primaryFixed, onColor = cs.onPrimaryFixed)
                ColorPaletteItem(color = cs.onPrimaryFixed, onColor = cs.primaryFixed)
            }
            Column {
                ColorPaletteItem(color = cs.primaryFixedDim, onColor = cs.onPrimaryFixed)
                ColorPaletteEmptyItem()
            }
            Column {
                ColorPaletteEmptyItem()
                ColorPaletteItem(color = cs.onPrimaryFixedVariant, onColor = cs.primaryFixed)
            }
            Column {
                ColorPaletteItem(color = cs.inversePrimary, onColor = cs.primary)
                ColorPaletteEmptyItem()
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Column {
                ColorPaletteItem(color = cs.secondary, onColor = cs.onSecondary)
                ColorPaletteItem(color = cs.onSecondary, onColor = cs.secondary)
            }
            Column {
                ColorPaletteItem(color = cs.secondaryContainer, onColor = cs.onSecondaryContainer)
                ColorPaletteItem(color = cs.onSecondaryContainer, onColor = cs.secondaryContainer)
            }
            Column {
                ColorPaletteItem(color = cs.secondaryFixed, onColor = cs.onSecondaryFixed)
                ColorPaletteItem(color = cs.onSecondaryFixed, onColor = cs.secondaryFixed)
            }
            Column {
                ColorPaletteItem(color = cs.secondaryFixedDim, onColor = cs.onSecondaryFixed)
                ColorPaletteEmptyItem()
            }
            Column {
                ColorPaletteEmptyItem()
                ColorPaletteItem(color = cs.onSecondaryFixedVariant, onColor = cs.secondaryFixed)
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Column {
                ColorPaletteItem(color = cs.tertiary, onColor = cs.onTertiary)
                ColorPaletteItem(color = cs.onTertiary, onColor = cs.tertiary)
            }
            Column {
                ColorPaletteItem(color = cs.tertiaryContainer, onColor = cs.onTertiaryContainer)
                ColorPaletteItem(color = cs.onTertiaryContainer, onColor = cs.tertiaryContainer)
            }
            Column {
                ColorPaletteItem(color = cs.tertiaryFixed, onColor = cs.onTertiaryFixed)
                ColorPaletteItem(color = cs.onTertiaryFixed, onColor = cs.tertiaryFixed)
            }
            Column {
                ColorPaletteItem(color = cs.tertiaryFixedDim, onColor = cs.onTertiaryFixed)
                ColorPaletteEmptyItem()
            }
            Column {
                ColorPaletteEmptyItem()
                ColorPaletteItem(color = cs.onTertiaryFixedVariant, onColor = cs.tertiaryFixed)
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Column {
                ColorPaletteItem(color = cs.error, onColor = cs.onError)
                ColorPaletteItem(color = cs.onError, onColor = cs.error)
            }
            Column {
                ColorPaletteItem(color = cs.errorContainer, onColor = cs.onErrorContainer)
                ColorPaletteItem(color = cs.onErrorContainer, onColor = cs.errorContainer)
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Column {
                ColorPaletteItem(color = cs.surface, onColor = cs.onSurface)
                ColorPaletteItem(color = cs.onSurface, onColor = cs.surface)
            }
            Column {
                ColorPaletteItem(color = cs.surfaceDim, onColor = cs.onSurface)
                ColorPaletteEmptyItem()
            }
            Column {
                ColorPaletteItem(color = cs.surfaceBright, onColor = cs.onSurface)
                ColorPaletteEmptyItem()
            }
            Column {
                ColorPaletteItem(color = cs.surfaceVariant, onColor = cs.onSurfaceVariant)
                ColorPaletteItem(color = cs.onSurfaceVariant, onColor = cs.surfaceVariant)
            }
            Column {
                ColorPaletteItem(color = cs.inverseSurface, onColor = cs.inverseOnSurface)
                ColorPaletteItem(color = cs.inverseOnSurface, onColor = cs.inverseSurface)
            }
            Column {
                ColorPaletteItem(color = cs.background, onColor = cs.onBackground)
                ColorPaletteItem(color = cs.onBackground, onColor = cs.background)
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            ColorPaletteItem(color = cs.surfaceContainerLowest, onColor = cs.onSurface)
            ColorPaletteItem(color = cs.surfaceContainerLow, onColor = cs.onSurface)
            ColorPaletteItem(color = cs.surfaceContainer, onColor = cs.onSurface)
            ColorPaletteItem(color = cs.surfaceContainerHigh, onColor = cs.onSurface)
            ColorPaletteItem(color = cs.surfaceContainerHighest, onColor = cs.onSurface)
            ColorPaletteItem(color = cs.surfaceTint, onColor = cs.surface)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            ColorPaletteItem(color = cs.outline)
            ColorPaletteItem(color = cs.outlineVariant)
            ColorPaletteItem(color = cs.scrim)
        }
    }
}

@Composable
private fun ColorPaletteItem(
    color: Color,
    modifier: Modifier = Modifier,
    onColor: Color? = null,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(width = 48.dp, height = 24.dp)
            .background(color = color),
    ) {
        if (onColor != null) {
            Text(
                text = stringResource(id = R.string.feature_pastel_color_palette_item_text),
                color = onColor,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Composable
private fun ColorPaletteEmptyItem(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .size(width = 48.dp, height = 24.dp),
    )
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
