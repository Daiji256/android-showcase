package io.github.daiji256.showcase.feature.navigation2arguments.viewmodelargsreceiver

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.feature.navigation2arguments.R
import io.github.daiji256.showcase.feature.navigation2arguments.ReceiverDocument

@Composable
internal fun ViewModelArgsReceiverScreen(
    onNavigateUpClick: () -> Unit,
    viewModel: ViewModelArgsReceiverViewModel = hiltViewModel(),
) {
    ViewModelArgsReceiverScreen(
        arg = viewModel.arg,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Composable
private fun ViewModelArgsReceiverScreen(
    arg: String,
    onNavigateUpClick: () -> Unit,
) {
    ReceiverDocument(
        arg = arg,
        title = stringResource(id = R.string.feature_nav2_args_receiver_view_model_title),
        methodMd = stringResource(id = R.string.feature_nav2_args_receiver_view_model_method_md),
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun ViewModelArgsReceiverScreenPreview() {
    ShowcaseTheme {
        ViewModelArgsReceiverScreen(
            arg = "Arg",
            onNavigateUpClick = {},
        )
    }
}
