package io.github.daiji256.showcase.feature.navigation2arguments.argssender

import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.markdown.Markdown
import io.github.daiji256.showcase.feature.navigation2arguments.R

@Composable
internal fun ArgsSenderScreen(
    onNavigateUpClick: () -> Unit,
    onReceiveArgDirectlyClick: (arg: String) -> Unit,
    onReceiveArgViaViewModelClick: (arg: String) -> Unit,
    onReceiveArgViaDiClick: (arg: String) -> Unit,
) {
    var arg by rememberSaveable { mutableStateOf("") }
    Document(
        title = stringResource(id = R.string.feature_nav2_args_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        Markdown(
            markdown = stringResource(id = R.string.feature_nav2_args_sender_doc_summary_md),
        )
        TextField(
            value = arg,
            label = {
                Markdown(
                    markdown = stringResource(
                        id = R.string.feature_nav2_args_sender_doc_arg_label_md,
                    ),
                )
            },
            onValueChange = { arg = it },
        )
        Button(
            onClick = dropUnlessResumed { onReceiveArgDirectlyClick(arg) },
        ) {
            Markdown(
                markdown = stringResource(
                    id = R.string.feature_nav2_args_sender_doc_direct_button_label_md,
                ),
            )
        }
        Button(
            onClick = dropUnlessResumed { onReceiveArgViaViewModelClick(arg) },
        ) {
            Markdown(
                markdown = stringResource(
                    id = R.string.feature_nav2_args_sender_doc_view_model_button_label_md,
                ),
            )
        }
        Button(
            onClick = dropUnlessResumed { onReceiveArgViaDiClick(arg) },
        ) {
            Markdown(
                markdown = stringResource(
                    id = R.string.feature_nav2_args_sender_doc_di_button_label_md,
                ),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ArgsSenderScreenPreview() {
    ShowcaseTheme {
        ArgsSenderScreen(
            onNavigateUpClick = {},
            onReceiveArgDirectlyClick = {},
            onReceiveArgViaViewModelClick = {},
            onReceiveArgViaDiClick = {},
        )
    }
}
