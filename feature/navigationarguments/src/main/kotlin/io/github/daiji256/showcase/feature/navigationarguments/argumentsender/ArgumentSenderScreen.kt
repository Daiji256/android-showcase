package io.github.daiji256.showcase.feature.navigationarguments.argumentsender

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
import io.github.daiji256.showcase.feature.navigationarguments.R

@Composable
internal fun ArgumentSenderScreen(
    onNavigateUpClick: () -> Unit,
    onNavigateToChild1ScreenClick: (value: String) -> Unit,
    onNavigateToChild2ScreenClick: (value: String) -> Unit,
    onNavigateToChild3ScreenClick: (value: String) -> Unit,
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
                text = "1",
            )
        }
        Button(
            onClick = dropUnlessResumed { onNavigateToChild2ScreenClick(value) },
        ) {
            Text(
                text = "2",
            )
        }
        Button(
            onClick = dropUnlessResumed { onNavigateToChild3ScreenClick(value) },
        ) {
            Text(
                text = "3",
            )
        }
    }
}

@Preview
@Composable
private fun ArgumentSenderScreenPreview() {
    // TODO: Theme
    ArgumentSenderScreen(
        onNavigateUpClick = {},
        onNavigateToChild1ScreenClick = {},
        onNavigateToChild2ScreenClick = {},
        onNavigateToChild3ScreenClick = {},
    )
}
