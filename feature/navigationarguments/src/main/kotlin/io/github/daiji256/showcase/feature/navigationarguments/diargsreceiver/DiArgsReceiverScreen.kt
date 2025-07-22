package io.github.daiji256.showcase.feature.navigationarguments.diargsreceiver

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.feature.navigationarguments.R
import io.github.daiji256.showcase.feature.navigationarguments.ReceiverDocument

@Composable
internal fun DiArgsReceiverScreen(
    onNavigateUpClick: () -> Unit,
    viewModel: DiArgsReceiverViewModel = hiltViewModel(),
) {
    DiArgsReceiverScreen(
        arg = viewModel.arg,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Composable
private fun DiArgsReceiverScreen(
    arg: String,
    onNavigateUpClick: () -> Unit,
) {
    ReceiverDocument(
        arg = arg,
        title = stringResource(id = R.string.feature_nav_args_receiver_di_title),
        methodMd = stringResource(id = R.string.feature_nav_args_receiver_di_method_md),
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun DiArgsReceiverScreenPreview() {
    ShowcaseTheme {
        DiArgsReceiverScreen(
            arg = "Arg",
            onNavigateUpClick = {},
        )
    }
}
