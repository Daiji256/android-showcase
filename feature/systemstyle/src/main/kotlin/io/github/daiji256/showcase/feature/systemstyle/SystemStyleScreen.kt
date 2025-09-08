package io.github.daiji256.showcase.feature.systemstyle

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun SystemStyleScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_system_style_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        SystemStyleValues()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SystemStyleScreenPreview() {
    ShowcaseTheme {
        SystemStyleScreen(
            onNavigateUpClick = {},
        )
    }
}
