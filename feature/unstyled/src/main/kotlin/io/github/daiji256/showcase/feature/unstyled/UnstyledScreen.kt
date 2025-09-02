package io.github.daiji256.showcase.feature.unstyled

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun UnstyledScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_unstyled_title),
        markdown = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun UnstyledScreenPreview() {
    ShowcaseTheme {
        UnstyledScreen(
            onNavigateUpClick = {},
        )
    }
}
