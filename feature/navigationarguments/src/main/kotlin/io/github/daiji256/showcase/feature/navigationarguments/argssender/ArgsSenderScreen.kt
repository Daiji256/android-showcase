package io.github.daiji256.showcase.feature.navigationarguments.argssender

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
internal fun ArgsSenderScreen(
    onNavigateUpClick: () -> Unit,
    onNavigateToDirectArgsReceiverScreenClick: (value: String) -> Unit,
    onNavigateToViewModelArgsReceiverScreenClick: (value: String) -> Unit,
    onNavigateToDiArgsReceiverScreenClick: (value: String) -> Unit,
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
            onClick = dropUnlessResumed { onNavigateToDirectArgsReceiverScreenClick(value) },
        ) {
            Text(
                text = "1",
            )
        }
        Button(
            onClick = dropUnlessResumed { onNavigateToViewModelArgsReceiverScreenClick(value) },
        ) {
            Text(
                text = "2",
            )
        }
        Button(
            onClick = dropUnlessResumed { onNavigateToDiArgsReceiverScreenClick(value) },
        ) {
            Text(
                text = "3",
            )
        }
    }
}

@Preview
@Composable
private fun ArgsSenderScreenPreview() {
    // TODO: Theme
    ArgsSenderScreen(
        onNavigateUpClick = {},
        onNavigateToDirectArgsReceiverScreenClick = {},
        onNavigateToViewModelArgsReceiverScreenClick = {},
        onNavigateToDiArgsReceiverScreenClick = {},
    )
}
