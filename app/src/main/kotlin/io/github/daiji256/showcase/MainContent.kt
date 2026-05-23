package io.github.daiji256.showcase

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator
import io.github.daiji256.showcase.core.ui.navigation.Navigator
import io.github.daiji256.showcase.core.ui.navigation.rememberNavState
import io.github.daiji256.showcase.core.ui.showcase.ShowcaseNavKey
import io.github.daiji256.showcase.core.ui.urihandler.SafeUriHandler
import io.github.daiji256.showcase.core.ui.window.LocalWindowShape

@Composable
internal fun MainContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val uriHandler = remember(context) { SafeUriHandler(context) }

    val navState = rememberNavState(start = ShowcaseNavKey)
    val navigator = remember(navState) { Navigator(state = navState) }

    CompositionLocalProvider(
        LocalUriHandler provides uriHandler,
        LocalNavigator provides navigator,
    ) {
        ShowcaseTheme {
            MainNavDisplay(
                navState = navState,
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = LocalWindowShape.current,
                    ),
            )
        }
    }
}
