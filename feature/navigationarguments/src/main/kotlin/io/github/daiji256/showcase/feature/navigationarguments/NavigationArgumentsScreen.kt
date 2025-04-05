package io.github.daiji256.showcase.feature.navigationarguments

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun NavigationArgumentsScreen(
    onNavigateUpClick: () -> Unit,
    onNavigateToChild1ScreenClick: (value: String) -> Unit,
) {
    var value by rememberSaveable { mutableStateOf("") }
    Document(
        title = stringResource(id = R.string.feature_navigation_arguments_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        TextField(
            value = value,
            onValueChange = { value = it },
        )
        Button(
            onClick = dropUnlessResumed { onNavigateToChild1ScreenClick(value) },
        ) {
            Text(
                text = "TODO",
            )
        }
    }
}

@Preview
@Composable
private fun NavigationArgumentsScreenPreview() {
    // TODO: Theme
    NavigationArgumentsScreen(
        onNavigateUpClick = {},
        onNavigateToChild1ScreenClick = {},
    )
}
