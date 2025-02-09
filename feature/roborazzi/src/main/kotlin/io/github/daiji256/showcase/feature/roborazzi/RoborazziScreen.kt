package io.github.daiji256.showcase.feature.roborazzi

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
fun RoborazziScreen() {
    Document(
        title = stringResource(id = R.string.feature_roborazzi_title),
        markdown = "TODO",
        onNavigateUpClick = { /*TODO*/ },
    )
}

@Preview
@Composable
private fun RoborazziScreenPreview() {
    // TODO: Theme
    RoborazziScreen()
}
