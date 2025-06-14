package io.github.daiji256.showcase.feature.safeurihandler

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.markdown.Markdown
import io.github.daiji256.showcase.core.ui.urihandler.SafeUriHandler

@Composable
internal fun SafeUriHandlerScreen(
    onNavigateUpClick: () -> Unit,
) {
    val context = LocalContext.current
    val safeUriHandler = remember(context) { SafeUriHandler(context) }
    SafeUriHandlerScreen(
        onNavigateUpClick = onNavigateUpClick,
        openUri = safeUriHandler::openUri,
    )
}

@Composable
private fun SafeUriHandlerScreen(
    onNavigateUpClick: () -> Unit,
    openUri: (uri: String) -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_safe_uri_handler_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        Markdown(
            markdown = stringResource(
                id = R.string.feature_safe_uri_handler_document_summary_md,
            ),
        )
        Markdown(
            markdown = stringResource(
                id = R.string.feature_safe_uri_handler_document_demos_header_md,
            ),
        )
        Markdown(
            markdown = stringResource(
                id = R.string.feature_safe_uri_handler_document_demos_can_open_md,
            ),
        )
        Button(onClick = { openUri(ExampleUri) }) {
            Text(
                text = stringResource(
                    id = R.string.feature_safe_uri_handler_document_demos_open_button,
                ),
            )
        }
        Markdown(
            markdown = stringResource(
                id = R.string.feature_safe_uri_handler_document_demos_cannot_open_md,
            ),
        )
        Button(onClick = { openUri(InvalidUri) }) {
            Text(
                text = stringResource(
                    id = R.string.feature_safe_uri_handler_document_demos_open_button,
                ),
            )
        }
    }
}

private const val ExampleUri = "https://example.com"
private const val InvalidUri = "invalid-uri"

@Preview
@Composable
private fun SafeUriHandlerScreenPreview() {
    // TODO: Theme
    SafeUriHandlerScreen(
        onNavigateUpClick = {},
        openUri = {},
    )
}
