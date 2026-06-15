package io.github.daiji256.showcase.feature.fallbackfont

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator

@Composable
internal fun FallbackFontScreen() {
    val navigator = LocalNavigator.current
    FallbackFontScreen(
        onNavigateUpClick = navigator::navigateUp,
    )
}

@Composable
private fun FallbackFontScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_fallback_font_title),
        markdown = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun FallbackFontScreenPreview() {
    ShowcaseTheme {
        FallbackFontScreen(
            onNavigateUpClick = {},
        )
    }
}
