package io.github.daiji256.showcase.feature.pullpaging

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PullPagingScreen(
    onNavigateUpClick: () -> Unit,
) {
    var itemCount by remember { mutableIntStateOf(10) }
    val coroutineScope = rememberCoroutineScope()

    var isReached by remember { mutableStateOf(false) }

    val pullToRefreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            delay(1500)
            itemCount = 10
            isRefreshing = false
            isReached = false
        }
    }

    val pullToAppendState = rememberPullToAppendState()
    var isAppending by remember { mutableStateOf(false) }
    val onAppend: () -> Unit = {
        isAppending = true
        coroutineScope.launch {
            delay(1500)
            itemCount += 10
            isAppending = false
            isReached = itemCount >= 30
        }
    }

    PullToRefreshBox(
        state = pullToRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
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
