package io.github.daiji256.showcase.feature.navnode.demo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseAnimations
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator
import io.github.daiji256.showcase.core.ui.navigation.NavNode
import io.github.daiji256.showcase.core.ui.navigation.Navigator
import io.github.daiji256.showcase.core.ui.navigation.rememberNavTree
import io.github.daiji256.showcase.core.ui.navigation.rememberNavTreeEntries
import io.github.daiji256.showcase.feature.navnode.R

@Composable
internal fun NavNodeDemoScreen(
    initial: DemoInitial,
) {
    val navigator = LocalNavigator.current
    NavNodeDemoScreen(
        initial = initial,
        onNavigateUpClick = navigator::navigateUp,
    )
}

@Composable
private fun NavNodeDemoScreen(
    initial: DemoInitial,
    onNavigateUpClick: () -> Unit,
) {
    val tree = rememberNavTree {
        NavNode.Stack(
            key = RootNavKey,
            children = listOf(
                when (initial) {
                    DemoInitial.Onboarding ->
                        NavNode.Leaf(key = OnboardingNavKey)

                    DemoInitial.NavigationBarA ->
                        NavNode.Select(
                            key = NavigationBarRootNavKey,
                            selected = NavigationBarARootNavKey,
                            children = setOf(
                                NavNode.Stack(
                                    key = NavigationBarARootNavKey,
                                    children = listOf(NavNode.Leaf(key = NavigationBarANavKey)),
                                ),
                                NavNode.Stack(
                                    key = NavigationBarBRootNavKey,
                                    children = listOf(NavNode.Leaf(key = NavigationBarBNavKey)),
                                ),
                                NavNode.Stack(
                                    key = NavigationBarCRootNavKey,
                                    children = listOf(NavNode.Leaf(key = NavigationBarCNavKey)),
                                ),
                            ),
                        )

                    DemoInitial.NavigationBarA1 ->
                        NavNode.Select(
                            key = NavigationBarRootNavKey,
                            selected = NavigationBarARootNavKey,
                            children = setOf(
                                NavNode.Stack(
                                    key = NavigationBarARootNavKey,
                                    children = listOf(
                                        NavNode.Leaf(
                                            up = NavNode.Leaf(key = NavigationBarANavKey),
                                            key = NavigationBarA1NavKey,
                                        ),
                                    ),
                                ),
                                NavNode.Stack(
                                    key = NavigationBarBRootNavKey,
                                    children = listOf(NavNode.Leaf(key = NavigationBarBNavKey)),
                                ),
                                NavNode.Stack(
                                    key = NavigationBarCRootNavKey,
                                    children = listOf(NavNode.Leaf(key = NavigationBarCNavKey)),
                                ),
                            ),
                        )

                    DemoInitial.Outer2 ->
                        NavNode.Leaf(
                            up = NavNode.Leaf(
                                up = NavNode.Select(
                                    key = NavigationBarRootNavKey,
                                    selected = NavigationBarCRootNavKey,
                                    children = setOf(
                                        NavNode.Stack(
                                            key = NavigationBarARootNavKey,
                                            children = listOf(
                                                NavNode.Leaf(key = NavigationBarANavKey),
                                            ),
                                        ),
                                        NavNode.Stack(
                                            key = NavigationBarBRootNavKey,
                                            children = listOf(
                                                NavNode.Leaf(key = NavigationBarBNavKey),
                                            ),
                                        ),
                                        NavNode.Stack(
                                            key = NavigationBarCRootNavKey,
                                            children = listOf(
                                                NavNode.Leaf(key = NavigationBarCNavKey),
                                            ),
                                        ),
                                    ),
                                ),
                                key = Outer1NavKey,
                            ),
                            key = Outer2NavKey,
                        )
                },
            ),
        )
    }
    val navigator = remember(tree) { Navigator(tree = tree) }
    val entries = rememberNavTreeEntries(
        tree = tree,
        entryDecorators = listOf(rememberSaveableStateHolderNavEntryDecorator()),
        entryProvider = entryProvider {
            onboarding()
            navigationBarA()
            navigationBarA1()
            navigationBarB()
            navigationBarBSwitchX()
            navigationBarBSwitchY()
            navigationBarC()
            outer1()
            outer2()
        },
    )

    Document(
        title = stringResource(id = R.string.feature_nav_node_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        // TODO
        Text(text = "NavDisplay:")
        CompositionLocalProvider(
            LocalNavigator provides navigator,
        ) {
            NavDisplay(
                entries = entries,
                onBack = navigator::pop,
                transitionSpec = ShowcaseAnimations.transitionSpec,
                popTransitionSpec = ShowcaseAnimations.popTransitionSpec,
                // TODO: predictivePopTransitionSpec,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .aspectRatio(3f / 4f)
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.LightGray), // TODO
            )
        }

        // TODO
        Text(text = "NavNode:")
        NavNodeView(
            node = tree,
            modifier = Modifier.padding(start = 8.dp),
        )
    }
}

@Composable
private fun NavNodeView(
    node: NavNode<NavKey>,
    modifier: Modifier = Modifier,
    current: Boolean = true,
) {
    Column(
        modifier = modifier
            .border(
                width = if (current) 2.dp else 1.dp,
                // TODO
                color = when (node) {
                    is NavNode.Leaf -> Color.Unspecified
                    is NavNode.Stack -> Color.Red
                    is NavNode.Select -> Color.Blue
                },
            )
            .padding(
                all = when (node) {
                    is NavNode.Leaf -> 0.dp
                    is NavNode.Stack, is NavNode.Select -> 8.dp
                },
            ),
    ) {
        when (node) {
            is NavNode.Leaf -> {
                // TODO
                Text(
                    text = node.key.toString(),
                    fontWeight = if (current) FontWeight.Bold else null,
                )
            }

            is NavNode.Stack -> {
                node.children.forEachIndexed { index, child ->
                    NavNodeView(
                        node = child,
                        current = current && index == node.children.lastIndex,
                    )
                }
            }

            is NavNode.Select -> {
                node.children.forEach {
                    NavNodeView(
                        node = it,
                        current = current && it.key == node.selected,
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NavNodeDemoScreenPreview() {
    ShowcaseTheme {
        // TODO
    }
}
