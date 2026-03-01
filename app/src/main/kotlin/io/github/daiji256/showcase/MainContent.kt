package io.github.daiji256.showcase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator
import io.github.daiji256.showcase.core.ui.navigation.NavNode
import io.github.daiji256.showcase.core.ui.navigation.Navigator
import io.github.daiji256.showcase.core.ui.navigation.rememberNavTree
import io.github.daiji256.showcase.core.ui.showcase.ShowcaseNavKey
import io.github.daiji256.showcase.core.ui.urihandler.SafeUriHandler
import kotlinx.serialization.Serializable

@Composable
internal fun MainContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val uriHandler = remember(context) { SafeUriHandler(context) }

    val navTree = rememberNavTree {
        NavNode.Stack(
            key = RootNavKey,
            children = listOf(NavNode.Leaf(key = ShowcaseNavKey)),
        )
    }
    val navigator = remember(navTree) { Navigator(tree = navTree) }

    CompositionLocalProvider(
        LocalUriHandler provides uriHandler,
        LocalNavigator provides navigator,
    ) {
        ShowcaseTheme {
            MainNavDisplay(
                tree = navTree,
                modifier = modifier,
            )
        }
    }
}

@Serializable
data object RootNavKey : NavKey
