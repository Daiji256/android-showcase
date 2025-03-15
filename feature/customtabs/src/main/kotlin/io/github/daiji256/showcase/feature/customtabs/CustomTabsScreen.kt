package io.github.daiji256.showcase.feature.customtabs

import android.net.Uri
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.markdown.Markdown
import io.github.daiji256.showcase.core.ui.navigation.LocalNavController

@Composable
internal fun CustomTabsScreen(viewModel: CustomTabsViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val customTabsLauncher = rememberCustomTabsLauncher()
    CustomTabsScreen(
        onNavigateUpClick = dropUnlessResumed(block = navController::navigateUp),
        launchCustomTabFromActivityContext = customTabsLauncher::launch,
        launchCustomTabFromApplicationContext = viewModel::launchCustomTabFromApplicationContext,
    )
}

@Composable
private fun CustomTabsScreen(
    onNavigateUpClick: () -> Unit,
    launchCustomTabFromActivityContext: (Uri) -> Unit,
    launchCustomTabFromApplicationContext: (Uri) -> Unit,
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
        Button(onClick = { launchCustomTabFromActivityContext(exampleUri) }) {
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
        Button(onClick = { launchCustomTabFromApplicationContext(exampleUri) }) {
            Text(
                text = stringResource(
                    id = R.string.feature_custom_tabs_document_demos_launch_button,
                ),
            )
        }
    }
}

private val exampleUri = "https://example.com".toUri()

@Preview
@Composable
private fun CustomTabsScreenPreview() {
    // TODO: Theme
    CustomTabsScreen(
        onNavigateUpClick = {},
        launchCustomTabFromActivityContext = {},
        launchCustomTabFromApplicationContext = {},
    )
}
