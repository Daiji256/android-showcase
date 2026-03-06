package io.github.daiji256.showcase.feature.navnode.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseAnimations
import io.github.daiji256.showcase.core.ui.component.NavigateUpButton
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator
import io.github.daiji256.showcase.core.ui.navigation.NavNode
import kotlinx.serialization.Serializable

@Serializable
internal data object RootNavKey : NavKey

@Serializable
internal data object OnboardingNavKey : NavKey

internal fun EntryProviderScope<NavKey>.onboarding() {
    entry<OnboardingNavKey> { key ->
        val navigator = LocalNavigator.current
        DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.navigate(
                            route = NavNode.Select(
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
                            ),
                            popUpTo = OnboardingNavKey,
                            inclusive = true,
                        )
                    },
                ) {
                    // TODO
                    Text("Navigate to NavigationBar")
                }
            },
        )
    }
}

@Serializable
internal data object NavigationBarRootNavKey : NavKey

@Serializable
internal data object NavigationBarARootNavKey : NavKey

@Serializable
internal data object NavigationBarANavKey : NavKey {
    val contentKey: String get() = this.toString()
}

internal fun EntryProviderScope<NavKey>.navigationBarA() {
    entry<NavigationBarANavKey>(
        clazzContentKey = { it.contentKey },
        metadata = NavDisplay.transitionSpec {
            when (this.initialState.entries.last().contentKey) {
                in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                else -> ShowcaseAnimations.transitionSpec(this)
            }
        } +
            NavDisplay.popTransitionSpec {
                when (this.initialState.entries.last().contentKey) {
                    in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            },
    ) { key ->
        val navigator = LocalNavigator.current
        DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.navigate(route = NavigationBarA1NavKey)
                    },
                ) {
                    // TODO
                    Text("Navigate to NavigationBarA1")
                }
            },
        )
    }
}

@Serializable
internal data object NavigationBarA1NavKey : NavKey {
    val contentKey: String get() = this.toString()
}

internal fun EntryProviderScope<NavKey>.navigationBarA1() {
    entry<NavigationBarA1NavKey>(
        clazzContentKey = { it.contentKey },
        metadata = NavDisplay.transitionSpec {
            when (this.initialState.entries.last().contentKey) {
                in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                else -> ShowcaseAnimations.transitionSpec(this)
            }
        } +
            NavDisplay.popTransitionSpec {
                when (this.initialState.entries.last().contentKey) {
                    in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            },
    ) { key ->
        DemoScaffold(key = key)
    }
}

@Serializable
internal data object NavigationBarBRootNavKey : NavKey

@Serializable
internal data object NavigationBarBNavKey : NavKey {
    val contentKey: String get() = this.toString()
}

internal fun EntryProviderScope<NavKey>.navigationBarB() {
    entry<NavigationBarBNavKey>(
        clazzContentKey = { it.contentKey },
        metadata = NavDisplay.transitionSpec {
            when (this.initialState.entries.last().contentKey) {
                in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                else -> ShowcaseAnimations.transitionSpec(this)
            }
        } +
            NavDisplay.popTransitionSpec {
                when (this.initialState.entries.last().contentKey) {
                    in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            },
    ) { key ->
        val navigator = LocalNavigator.current
        DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.navigate(
                            route = NavNode.Select(
                                key = NavigationBarBSwitchRootNavKey,
                                selected = NavigationBarBSwitchXNavKey,
                                children = setOf(
                                    NavNode.Leaf(key = NavigationBarBSwitchXNavKey),
                                    NavNode.Leaf(key = NavigationBarBSwitchYNavKey),
                                ),
                            ),
                        )
                    },
                ) {
                    // TODO
                    Text("Navigate to NavigationBarBSwitch")
                }
            },
        )
    }
}

@Serializable
internal data object NavigationBarBSwitchRootNavKey : NavKey

@Serializable
internal data object NavigationBarBSwitchXNavKey : NavKey {
    val contentKey: String get() = this.toString()
}

internal fun EntryProviderScope<NavKey>.navigationBarBSwitchX() {
    entry<NavigationBarBSwitchXNavKey>(
        clazzContentKey = { it.contentKey },
        metadata = NavDisplay.transitionSpec {
            when (this.initialState.entries.last().contentKey) {
                in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                else -> ShowcaseAnimations.transitionSpec(this)
            }
        } +
            NavDisplay.popTransitionSpec {
                when (this.initialState.entries.last().contentKey) {
                    in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            },
    ) { key ->
        val navigator = LocalNavigator.current
        DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.navigate(route = NavigationBarBSwitchYNavKey)
                    },
                ) {
                    // TODO
                    Text("Switch to NavigationBarBSwitchY")
                }
            },
        )
    }
}

@Serializable
internal data object NavigationBarBSwitchYNavKey : NavKey {
    val contentKey: String get() = this.toString()
}

internal fun EntryProviderScope<NavKey>.navigationBarBSwitchY() {
    entry<NavigationBarBSwitchYNavKey>(
        clazzContentKey = { it.contentKey },
        metadata = NavDisplay.transitionSpec {
            when (this.initialState.entries.last().contentKey) {
                in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                else -> ShowcaseAnimations.transitionSpec(this)
            }
        } +
            NavDisplay.popTransitionSpec {
                when (this.initialState.entries.last().contentKey) {
                    in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            },
    ) { key ->
        val navigator = LocalNavigator.current
        DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.navigate(route = NavigationBarBSwitchXNavKey)
                    },
                ) {
                    // TODO
                    Text("Switch to NavigationBarBSwitchX")
                }
            },
        )
    }
}

@Serializable
internal data object NavigationBarCRootNavKey : NavKey

@Serializable
internal data object NavigationBarCNavKey : NavKey {
    val contentKey: String get() = this.toString()
}

internal fun EntryProviderScope<NavKey>.navigationBarC() {
    entry<NavigationBarCNavKey>(
        clazzContentKey = { it.contentKey },
        metadata = NavDisplay.transitionSpec {
            when (this.initialState.entries.last().contentKey) {
                in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                Outer1NavKey.contentKey -> ShowcaseAnimations.popTransitionSpec(this)
                else -> ShowcaseAnimations.transitionSpec(this)
            }
        } +
            NavDisplay.popTransitionSpec {
                when (this.initialState.entries.last().contentKey) {
                    in NavigationBarContentKeys -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            },
    ) { key ->
        val navigator = LocalNavigator.current
        DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.navigate(route = Outer1NavKey)
                    },
                ) {
                    // TODO
                    Text("Navigate to Outer1")
                }
            },
        )
    }
}

@Serializable
internal data object Outer1NavKey : NavKey {
    val contentKey: String get() = this.toString()
}

internal fun EntryProviderScope<NavKey>.outer1() {
    entry<Outer1NavKey>(
        clazzContentKey = { it.contentKey },
        metadata = NavDisplay.transitionSpec {
            when (this.initialState.entries.last().contentKey) {
                Outer2NavKey.contentKey -> ShowcaseAnimations.popTransitionSpec(this)
                else -> ShowcaseAnimations.transitionSpec(this)
            }
        },
    ) { key ->
        val navigator = LocalNavigator.current
        DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.navigate(route = Outer2NavKey)
                    },
                ) {
                    // TODO
                    Text("Navigate to Outer2")
                }
            },
        )
    }
}

@Serializable
internal data object Outer2NavKey : NavKey {
    val contentKey: String get() = this.toString()
}

internal fun EntryProviderScope<NavKey>.outer2() {
    entry<Outer2NavKey>(
        clazzContentKey = { it.contentKey },
    ) { key ->
        DemoScaffold(key = key)
    }
}

private val NavigationBarContentKeys = setOf(
    NavigationBarANavKey.contentKey,
    NavigationBarA1NavKey.contentKey,
    NavigationBarBNavKey.contentKey,
    NavigationBarBSwitchXNavKey.contentKey,
    NavigationBarBSwitchYNavKey.contentKey,
    NavigationBarCNavKey.contentKey,
)

@Composable
private fun DemoScaffold(
    key: NavKey,
    buttons: @Composable ColumnScope.() -> Unit = {},
) {
    val navigator = LocalNavigator.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = key.toString())
                },
                navigationIcon = {
                    when (key) {
                        is NavigationBarA1NavKey,
                        is NavigationBarBSwitchXNavKey,
                        is NavigationBarBSwitchYNavKey,
                        is Outer1NavKey,
                        is Outer2NavKey,
                            -> {
                            NavigateUpButton(
                                onClick = dropUnlessResumed { navigator.navigateUp() },
                            )
                        }

                        else -> Unit
                    }
                },
                windowInsets = WindowInsets(0, 0, 0, 0),
            )
        },
        bottomBar = {
            val selected = when (key) {
                is NavigationBarANavKey,
                is NavigationBarA1NavKey,
                    -> NavigationBarARootNavKey

                is NavigationBarBNavKey,
                is NavigationBarBSwitchXNavKey,
                is NavigationBarBSwitchYNavKey,
                    -> NavigationBarBRootNavKey

                is NavigationBarCNavKey,
                    -> NavigationBarCRootNavKey

                else -> null
            }
            if (selected != null) {
                NavigationBar(
                    windowInsets = WindowInsets(0, 0, 0, 0),
                ) {
                    NavigationBarItem(
                        selected = selected == NavigationBarARootNavKey,
                        onClick = dropUnlessResumed {
                            navigator.navigate(route = NavigationBarARootNavKey)
                        },
                        icon = { Text("A") }, // TODO
                    )
                    NavigationBarItem(
                        selected = selected == NavigationBarBRootNavKey,
                        onClick = dropUnlessResumed {
                            navigator.navigate(route = NavigationBarBRootNavKey)
                        },
                        icon = { Text("B") }, // TODO
                    )
                    NavigationBarItem(
                        selected = selected == NavigationBarCRootNavKey,
                        onClick = dropUnlessResumed {
                            navigator.navigate(route = NavigationBarCRootNavKey)
                        },
                        icon = { Text("C") }, // TODO
                    )
                }
            }
        },
    ) { padding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            var count by rememberSaveable { mutableIntStateOf(0) }
            Button(onClick = { count++ }) {
                // TODO
                Text("Count: $count")
            }

            buttons()
        }
    }
}
