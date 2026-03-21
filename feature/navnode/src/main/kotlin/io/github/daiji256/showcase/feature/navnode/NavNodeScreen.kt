package io.github.daiji256.showcase.feature.navnode

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator
import io.github.daiji256.showcase.feature.navnode.demo.DemoInitial
import io.github.daiji256.showcase.feature.navnode.demo.NavNodeDemoNavKey

@Composable
internal fun NavNodeScreen() {
    val navigator = LocalNavigator.current
    NavNodeScreen(
        onNavigateUpClick = navigator::navigateUp,
        onNavigateToDemo = { navigator.push(key = NavNodeDemoNavKey(initial = it)) },
    )
}

@Composable
private fun NavNodeScreen(
    onNavigateUpClick: () -> Unit,
    onNavigateToDemo: (DemoInitial) -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_nav_node_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        DemoInitial.entries.forEach { initial ->
            Button(
                onClick = { onNavigateToDemo(initial) },
            ) {
                // TODO
                Text(text = "Navigate to Demo($initial)")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NavNodeScreenPreview() {
    ShowcaseTheme {
        NavNodeScreen(
            onNavigateUpClick = {},
            onNavigateToDemo = {},
        )
    }
}
