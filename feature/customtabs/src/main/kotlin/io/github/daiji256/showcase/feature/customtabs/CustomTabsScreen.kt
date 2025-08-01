package io.github.daiji256.showcase.feature.customtabs

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.markdown.Markdown

@Composable
internal fun CustomTabsScreen(
    onNavigateUpClick: () -> Unit,
    viewModel: CustomTabsViewModel = hiltViewModel(),
) {
    val customTabsLauncher = rememberCustomTabsLauncher()
    CustomTabsScreen(
        onNavigateUpClick = onNavigateUpClick,
        launchCustomTabFromActivityContext = { uri ->
            customTabsLauncher.launch(uri = uri.toUri())
        },
        launchCustomTabFromApplicationContext = { uri ->
            viewModel.launchCustomTabFromApplicationContext(uri = uri.toUri())
        },
    )
}

@Composable
private fun CustomTabsScreen(
    onNavigateUpClick: () -> Unit,
    launchCustomTabFromActivityContext: (uri: String) -> Unit,
    launchCustomTabFromApplicationContext: (uri: String) -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_custom_tabs_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        Markdown(
            markdown = stringResource(
                id = R.string.feature_custom_tabs_document_summary_md,
            ),
        )
        Markdown(
            markdown = stringResource(
                id = R.string.feature_custom_tabs_document_demos_header_md,
            ),
        )
        Markdown(
            markdown = stringResource(
                id = R.string.feature_custom_tabs_document_demos_activity_context_md,
            ),
        )
        Button(onClick = { launchCustomTabFromActivityContext(ExampleUri) }) {
            Text(
                text = stringResource(
                    id = R.string.feature_custom_tabs_document_demos_launch_button,
                ),
            )
        }
        Markdown(
            markdown = stringResource(
                id = R.string.feature_custom_tabs_document_demos_application_context_md,
            ),
        )
        Button(onClick = { launchCustomTabFromApplicationContext(ExampleUri) }) {
            Text(
                text = stringResource(
                    id = R.string.feature_custom_tabs_document_demos_launch_button,
                ),
            )
        }
    }
}

private const val ExampleUri = "https://example.com"

@Preview(showSystemUi = true)
@Composable
private fun CustomTabsScreenPreview() {
    ShowcaseTheme {
        CustomTabsScreen(
            onNavigateUpClick = {},
            launchCustomTabFromActivityContext = {},
            launchCustomTabFromApplicationContext = {},
        )
    }
}
