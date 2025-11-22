package io.github.daiji256.showcase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.urihandler.SafeUriHandler

@Composable
internal fun MainContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val uriHandler = remember(context) { SafeUriHandler(context) }

    CompositionLocalProvider(
        LocalUriHandler provides uriHandler,
    ) {
        ShowcaseTheme {
            MainNavDisplay(
                modifier = modifier,
            )
        }
    }
}
