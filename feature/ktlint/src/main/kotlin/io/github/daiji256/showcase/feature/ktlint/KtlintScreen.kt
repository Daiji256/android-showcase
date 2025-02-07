package io.github.daiji256.showcase.feature.ktlint

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.markdown.fromMarkdown

@Composable
fun KtlintScreen(
    modifier: Modifier = Modifier,
) {
    Document(
        title = stringResource(id = R.string.title),
        text = AnnotatedString.fromMarkdown(
            markdownString = stringResource(id = R.string.document_md),
        ),
        onNavigateUpClick = { /*TODO*/ },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun KtlintScreenPreview() {
    // TODO: Theme
    KtlintScreen()
}
