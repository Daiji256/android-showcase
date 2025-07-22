package io.github.daiji256.showcase.feature.navigationarguments.directargsreceiver

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.feature.navigationarguments.R
import io.github.daiji256.showcase.feature.navigationarguments.ReceiverDocument

@Composable
internal fun DirectArgsReceiverScreen(
    arg: String,
    onNavigateUpClick: () -> Unit,
) {
    ReceiverDocument(
        arg = arg,
        title = stringResource(id = R.string.feature_nav_args_receiver_direct_title),
        methodMd = stringResource(id = R.string.feature_nav_args_receiver_direct_method_md),
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun DirectArgsReceiverScreenPreview() {
    ShowcaseTheme {
        DirectArgsReceiverScreen(
            arg = "Arg",
            onNavigateUpClick = {},
        )
    }
}
