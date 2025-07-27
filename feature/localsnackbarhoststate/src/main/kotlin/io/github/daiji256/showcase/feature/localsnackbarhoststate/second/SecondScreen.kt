package io.github.daiji256.showcase.feature.localsnackbarhoststate.second

import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.localsnackbarhoststate.LocalSnackbarHostState
import io.github.daiji256.showcase.feature.localsnackbarhoststate.R
import kotlinx.coroutines.launch

@Composable
internal fun SecondScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_lshs_second_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        val snackbarHostState = LocalSnackbarHostState.current
        val coroutineScope = rememberCoroutineScope()
        val snackbarMessage = stringResource(id = R.string.feature_lshs_second_snackbar_message)

        Button(
            onClick = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = snackbarMessage,
                        duration = SnackbarDuration.Long,
                    )
                }
            },
        ) {
            Text(
                text = stringResource(
                    id = R.string.feature_lshs_second_document_show_snackbar_button,
                ),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SecondScreenPreview() {
    ShowcaseTheme {
        SecondScreen(
            onNavigateUpClick = {},
        )
    }
}
