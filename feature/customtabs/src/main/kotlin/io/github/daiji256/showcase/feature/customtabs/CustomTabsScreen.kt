package io.github.daiji256.showcase.feature.customtabs

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.navigation.LocalNavController

@Composable
internal fun CustomTabsScreen(viewModel: CustomTabsViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    CustomTabsScreen(
        onNavigateUpClick = dropUnlessResumed(block = navController::navigateUp),
        onOpenUriClick = viewModel::onOpenUriClick,
    )
}

@Composable
private fun CustomTabsScreen(
    onNavigateUpClick: () -> Unit,
    onOpenUriClick: (Uri) -> Unit,
) {
    // TODO
    Column(modifier = Modifier.systemBarsPadding()) {
        Button(onClick = { onOpenUriClick("https://example.com".toUri()) }) {
            Text("https://example.com")
        }
    }
}

@Preview
@Composable
private fun CustomTabsScreenPreview() {
    // TODO: Theme
    CustomTabsScreen(
        onNavigateUpClick = {},
        onOpenUriClick = {},
    )
}
