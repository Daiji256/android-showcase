package io.github.daiji256.showcase.feature.navigationarguments

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.markdown.Markdown

@Composable
internal fun ReceiverDocument(
    arg: String,
    title: String,
    methodMd: String,
    onNavigateUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Document(
        title = title,
        onNavigateUpClick = onNavigateUpClick,
        modifier = modifier,
    ) {
        ArgView(arg = arg)
        Markdown(markdown = methodMd)
    }
}

@Composable
private fun ArgView(arg: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Markdown(
            markdown = stringResource(id = R.string.feature_nav_args_receiver_arg_label_md),
            modifier = Modifier
                .alignByBaseline(),
        )
        Text(
            text = arg,
            modifier = Modifier
                .alignByBaseline()
                .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
                .padding(4.dp),
        )
    }
}
