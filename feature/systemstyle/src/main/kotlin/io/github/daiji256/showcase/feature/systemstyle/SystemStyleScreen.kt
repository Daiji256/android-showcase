package io.github.daiji256.showcase.feature.systemstyle

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.markdown.Markdown

@Composable
internal fun SystemStyleScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_system_style_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        Markdown(
            markdown = stringResource(id = R.string.feature_system_style_document_md),
        )
        SystemStyleValues()
    }
}

@Preview(showSystemUi = true)
@Preview(fontScale = 1.15f, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun SystemStyleScreenPreview() {
    ShowcaseTheme {
        SystemStyleScreen(
            onNavigateUpClick = {},
        )
    }
}
