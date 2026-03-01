package io.github.daiji256.showcase.feature.roborazzi

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator

@Composable
internal fun RoborazziScreen() {
    val navigator = LocalNavigator.current
    RoborazziScreen(
        onNavigateUpClick = navigator::navigateUp,
    )
}

@Composable
private fun RoborazziScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_roborazzi_title),
        markdown = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun RoborazziScreenPreview() {
    ShowcaseTheme {
        RoborazziScreen(
            onNavigateUpClick = {},
        )
    }
}
