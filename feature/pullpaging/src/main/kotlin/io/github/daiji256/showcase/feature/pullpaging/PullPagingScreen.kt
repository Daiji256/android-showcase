package io.github.daiji256.showcase.feature.pullpaging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PullPagingScreen(
    onNavigateUpClick: () -> Unit,
) {
    var failure by remember { mutableStateOf(false) }
    var isReached by remember { mutableStateOf(false) }

    var itemCount by remember { mutableIntStateOf(10) }
    val coroutineScope = rememberCoroutineScope()

    val pullToRefreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            delay(1500)
            if (failure) return@launch
            itemCount = 10
        }.invokeOnCompletion {
            isRefreshing = false
        }
    }

    val pullToAppendState = rememberPullToAppendState()
    var isAppending by remember { mutableStateOf(false) }
    val onAppend: () -> Unit = {
        isAppending = true
        coroutineScope.launch {
            delay(1500)
            if (failure) return@launch
            itemCount += 10
        }.invokeOnCompletion {
            isAppending = false
        }
    }

    PullToRefreshBox(
        state = pullToRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier.statusBarsPadding(),
    ) {
        LazyColumn(
            contentPadding = WindowInsets.navigationBars.asPaddingValues(),
            modifier = Modifier
                .pullToAppend(
                    state = pullToAppendState,
                    isAppending = isAppending,
                    enabled = !isReached,
                    onAppend = onAppend,
                )
                .fillMaxSize(),
        ) {
            items(itemCount) {
                ListItem(
                    headlineContent = {
                        Text(text = "Item ${it + 1}")
                    },
                )
            }

            item {
                AppendIndicator(
                    isAppending = isAppending,
                    isReached = isReached,
                    pullToAppendState = pullToAppendState,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd),
        ) {
            Checkbox(
                checked = isReached,
                onCheckedChange = { isReached = it },
            )
            Checkbox(
                checked = failure,
                onCheckedChange = { failure = it },
            )
        }
    }
}

@Preview
@Composable
private fun PullPagingScreenPreview() {
    // TODO: Theme
    PullPagingScreen(
        onNavigateUpClick = {},
    )
}
