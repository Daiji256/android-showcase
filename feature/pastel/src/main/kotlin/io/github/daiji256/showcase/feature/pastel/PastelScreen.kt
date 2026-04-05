package io.github.daiji256.showcase.feature.pastel

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
        markdown = "TODO",
        onNavigateUpClick = onNavigateUpClick,
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
