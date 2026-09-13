package io.github.daiji256.showcase.feature.navnode.demo

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseAnimations
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.markdown.Markdown
import io.github.daiji256.showcase.core.ui.navigation.InternalNavStateApi
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator
import io.github.daiji256.showcase.core.ui.navigation.NavNode
import io.github.daiji256.showcase.core.ui.navigation.NavState
import io.github.daiji256.showcase.core.ui.navigation.Navigator
import io.github.daiji256.showcase.core.ui.navigation.createNavState
import io.github.daiji256.showcase.core.ui.navigation.rememberNavState
import io.github.daiji256.showcase.core.ui.navigation.toDecoratedNavEntries
import io.github.daiji256.showcase.core.ui.window.LocalWindowShape
import io.github.daiji256.showcase.feature.navnode.R

@Composable
internal fun NavNodeDemoScreen(
    initial: DemoInitial,
) {
    val navigator = LocalNavigator.current
    val navState = rememberNavState(
        start = when (initial) {
            DemoInitial.Onboarding -> OnboardingNavKey
            DemoInitial.NavigationBarA -> NavigationBarANavKey
            DemoInitial.NavigationBarA1 -> NavigationBarA1NavKey
            DemoInitial.Outer2 -> Outer2NavKey
        },
        pending = when (initial) {
            DemoInitial.Onboarding -> mutableListOf()
            DemoInitial.NavigationBarA -> mutableListOf()
            DemoInitial.NavigationBarA1 -> mutableListOf(NavigationBarANavKey)
            DemoInitial.Outer2 -> mutableListOf(NavigationBarCNavKey, Outer1NavKey)
        },
    )
    SharedTransitionLayout {
        NavNodeDemoScreen(
            navState = navState,
            onNavigateUpClick = navigator::navigateUp,
        )
    }
}

@Composable
context(_: SharedTransitionScope)
private fun NavNodeDemoScreen(
    navState: NavState,
    onNavigateUpClick: () -> Unit,
) {
    val navigator = remember(navState) { Navigator(state = navState) }
    val entries = navState.toDecoratedNavEntries(
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
        Markdown(
            markdown = stringResource(
                id = R.string.feature_nav_node_demo_nav_display_header_md,
            ),
        )
        CompositionLocalProvider(
            LocalNavigator provides navigator,
        ) {
            val shape = MaterialTheme.shapes.large
            CompositionLocalProvider(
                LocalWindowShape provides shape,
            ) {
                NavDisplay(
                    entries = entries,
                    onBack = navigator::pop,
                    transitionSpec = ShowcaseAnimations.transitionSpec,
                    popTransitionSpec = ShowcaseAnimations.popTransitionSpec,
                    predictivePopTransitionSpec = ShowcaseAnimations.predictivePopTransitionSpec,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .aspectRatio(3f / 4f)
                        .fillMaxWidth()
                        .dropShadow(shape = shape, shadow = Shadow(radius = 1.dp))
                        .background(shape = shape, color = MaterialTheme.colorScheme.surfaceVariant)
                        .clip(shape = shape),
                )
            }
        }

        Markdown(
            markdown = stringResource(
                id = R.string.feature_nav_node_demo_nav_state_header_md,
            ),
        )
        Column {
            Text(
                text = stringResource(id = R.string.feature_nav_node_demo_root_label),
                fontWeight = FontWeight.Bold,
            )
            NavNodeView(node = navState.root)

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = stringResource(id = R.string.feature_nav_node_demo_pending_label),
                fontWeight = FontWeight.Bold,
            )
            if (navState.pending.isEmpty()) {
                Text(text = stringResource(id = R.string.feature_nav_node_demo_empty_label))
            } else {
                Text(text = navState.pending.toList().toString())
            }
        }
    }
}

@Composable
private fun NavNodeView(
    node: NavNode,
    modifier: Modifier = Modifier,
    isActive: Boolean = true,
) {
    Column(modifier = modifier) {
        Text(
            text = node.key.toString(),
            fontWeight = if (isActive) FontWeight.Bold else null,
        )
        if (node.children.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .border(
                        width = if (isActive) 2.dp else 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                    )
                    .padding(8.dp),
            ) {
                node.children.forEach {
                    NavNodeView(
                        node = it,
                        isActive = isActive && it == node.currentChild,
                    )
                }
            }
        }
    }
}

@OptIn(InternalNavStateApi::class)
private class NavStatePreviewParameterProvider : PreviewParameterProvider<NavState> {
    override val values: Sequence<NavState> = sequenceOf(
        createNavState(
            start = OnboardingNavKey,
            pending = listOf(),
        ),
        createNavState(
            start = NavigationBarANavKey,
            pending = listOf(),
        ),
        createNavState(
            start = Outer2NavKey,
            pending = listOf(NavigationBarCNavKey, Outer1NavKey),
        ),
        createNavState(
            start = NavigationBarANavKey,
            pending = listOf(NavigationBarCNavKey, Outer1NavKey),
        ).also { state ->
            val navigator = Navigator(state = state)
            navigator.push(NavigationBarA1NavKey)
            navigator.switch(NavigationBarANavKey, NavigationBarBNavKey)
            navigator.push(NavigationBarBSwitchXNavKey)
        },
    )
}

@Preview(showSystemUi = true)
@Composable
private fun NavNodeDemoScreenPreview(
    @PreviewParameter(NavStatePreviewParameterProvider::class) navState: NavState,
) {
    ShowcaseTheme {
        SharedTransitionLayout {
            NavNodeDemoScreen(
                navState = remember { navState },
                onNavigateUpClick = {},
            )
        }
    }
}
