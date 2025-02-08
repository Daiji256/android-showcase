package io.github.daiji256.showcase.feature.roborazzi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
fun RoborazziScreen(
    modifier: Modifier = Modifier,
) {
    Document(
        title = stringResource(id = R.string.feature_roborazzi_title),
        markdown = "TODO",
        onNavigateUpClick = { /*TODO*/ },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun RoborazziScreenPreview() {
    // TODO: Theme
    RoborazziScreen()
}
