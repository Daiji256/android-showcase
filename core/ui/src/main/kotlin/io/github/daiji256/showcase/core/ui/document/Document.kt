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
import androidx.compose.material3.Button
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.daiji256.showcase.core.ui.component.NavigateUpButton
import io.github.daiji256.showcase.core.ui.localsnackbarhoststate.LocalSnackbarHostState
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
    val snackbarHostState = remember { SnackbarHostState() }
    CompositionLocalProvider(
        LocalSnackbarHostState provides snackbarHostState,
    ) {
        Scaffold(
            topBar = {
                LargeTopAppBar(
                    title = {
                        Text(text = title)
                    },
                    navigationIcon = {
                        NavigateUpButton(onClick = onNavigateUpClick)
                    },
                    scrollBehavior = topAppBarScrollBehavior,
                )
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
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
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = padding.calculateBottomPadding() + 24.dp),
            ) {
                content()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DocumentMarkdownPreview() {
    Document(
        title = "Title",
        markdown = "text ".repeat(1000).trimEnd(),
        onNavigateUpClick = {},
    )
}

@Preview(showSystemUi = true)
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
