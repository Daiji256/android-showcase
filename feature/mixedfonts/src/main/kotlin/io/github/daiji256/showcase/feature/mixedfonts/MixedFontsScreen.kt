package io.github.daiji256.showcase.feature.mixedfonts

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator

@Composable
internal fun MixedFontsScreen() {
    val navigator = LocalNavigator.current
    MixedFontsScreen(
        onNavigateUpClick = navigator::navigateUp,
    )
}

@Composable
private fun MixedFontsScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_mixed_fonts_title),
        markdown = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun MixedFontsScreenPreview() {
    ShowcaseTheme {
        MixedFontsScreen(
            onNavigateUpClick = {},
        )
    }
}
