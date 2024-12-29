package io.github.daiji256.sandbox.core.ui.document

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Document(
    title: String,
    text: String,
    onNavigateUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Document(
        title = title,
        text = AnnotatedString(text),
        onNavigateUpClick = onNavigateUpClick,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Document(
    title: String,
    text: AnnotatedString,
    onNavigateUpClick: () -> Unit,
    modifier: Modifier = Modifier,
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
                    IconButton(onClick = onNavigateUpClick) {
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
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
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
        )
    }
}

@Preview
@Composable
private fun DocumentPreview() {
    Document(
        title = "Title",
        text = "text ".repeat(1000).trimEnd(),
        onNavigateUpClick = {},
    )
}
