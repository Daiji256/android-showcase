package io.github.daiji256.showcase.core.ui.document

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.markdown.Markdown

@Composable
fun Document(
    title: String,
    markdown: String,
    onNavigateUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Document(
        title = title,
        onNavigateUpClick = onNavigateUpClick,
        modifier = modifier,
    ) {
        Markdown(
            markdown = markdown,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Document(
    title: String,
    onNavigateUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scrollState = rememberScrollState()
    val topAppBarState = rememberTopAppBarState()
    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        state = topAppBarState,
        canScroll = {
            topAppBarState.collapsedFraction != 0f ||
                scrollState.canScrollForward ||
                scrollState.canScrollBackward
        },
    )
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = dropUnlessResumed(block = onNavigateUpClick)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null, // TODO
                        )
                    }
                },
                scrollBehavior = topAppBarScrollBehavior,
            )
        },
        modifier = modifier
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
    ) { padding ->
        val layoutDirection = LocalLayoutDirection.current
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    start = padding.calculateStartPadding(layoutDirection),
                    end = padding.calculateEndPadding(layoutDirection),
                )
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(bottom = padding.calculateBottomPadding()),
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun DocumentMarkdownPreview() {
    Document(
        title = "Title",
        markdown = "text ".repeat(1000).trimEnd(),
        onNavigateUpClick = {},
    )
}

@Preview
@Composable
private fun DocumentContentPreview() {
    Document(
        title = "Title",
        onNavigateUpClick = {},
    ) {
        Markdown(markdown = "## H2\n\nParagraph")
        Button(onClick = {}) { Text("Button") }
    }
}
