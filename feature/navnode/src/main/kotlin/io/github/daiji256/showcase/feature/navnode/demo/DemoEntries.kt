package io.github.daiji256.showcase.feature.navnode.demo

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Transition
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
import androidx.navigation3.runtime.metadata
import androidx.navigation3.scene.Scene
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseAnimations
import io.github.daiji256.showcase.core.ui.component.NavigateUpButton
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator
import kotlinx.serialization.Serializable

@Serializable
internal data object OnboardingNavKey : NavKey

context(sharedTransitionScope: SharedTransitionScope)
internal fun EntryProviderScope<NavKey>.onboarding() {
    entry<OnboardingNavKey> { key ->
        val navigator = LocalNavigator.current
        sharedTransitionScope.DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.restart(start = NavigationBarANavKey)
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
internal data object NavigationBarANavKey : NavKey {
    val contentKey: String get() = this.toString()
}

context(sharedTransitionScope: SharedTransitionScope)
internal fun EntryProviderScope<NavKey>.navigationBarA() {
    entry<NavigationBarANavKey>(
        clazzContentKey = { it.contentKey },
        metadata = metadata {
            put(NavDisplay.TransitionKey) {
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.transitionSpec(this)
                }
            }
            put(NavDisplay.PopTransitionKey) {
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            }
        },
    ) { key ->
        val navigator = LocalNavigator.current
        sharedTransitionScope.DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.push(key = NavigationBarA1NavKey)
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

context(sharedTransitionScope: SharedTransitionScope)
internal fun EntryProviderScope<NavKey>.navigationBarA1() {
    entry<NavigationBarA1NavKey>(
        clazzContentKey = { it.contentKey },
        metadata = metadata {
            put(NavDisplay.TransitionKey) {
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.transitionSpec(this)
                }
            }
            put(NavDisplay.PopTransitionKey) {
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            }
        },
    ) { key ->
        sharedTransitionScope.DemoScaffold(key = key)
    }
}

@Serializable
internal data object NavigationBarBNavKey : NavKey {
    val contentKey: String get() = this.toString()
}

context(sharedTransitionScope: SharedTransitionScope)
internal fun EntryProviderScope<NavKey>.navigationBarB() {
    entry<NavigationBarBNavKey>(
        clazzContentKey = { it.contentKey },
        metadata = metadata {
            put(NavDisplay.TransitionKey) {
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.transitionSpec(this)
                }
            }
            put(NavDisplay.PopTransitionKey) {
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            }
        },
    ) { key ->
        val navigator = LocalNavigator.current
        sharedTransitionScope.DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.push(key = NavigationBarBSwitchXNavKey)
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
internal data object NavigationBarBSwitchXNavKey : NavKey {
    val contentKey: String get() = this.toString()
}

context(sharedTransitionScope: SharedTransitionScope)
internal fun EntryProviderScope<NavKey>.navigationBarBSwitchX() {
    entry<NavigationBarBSwitchXNavKey>(
        clazzContentKey = { it.contentKey },
        metadata = metadata {
            put(NavDisplay.TransitionKey) {
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.transitionSpec(this)
                }
            }
            put(NavDisplay.PopTransitionKey) {
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            }
        },
    ) { key ->
        val navigator = LocalNavigator.current
        sharedTransitionScope.DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.switch(
                            from = NavigationBarBSwitchXNavKey,
                            to = NavigationBarBSwitchYNavKey,
                        )
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

context(sharedTransitionScope: SharedTransitionScope)
internal fun EntryProviderScope<NavKey>.navigationBarBSwitchY() {
    entry<NavigationBarBSwitchYNavKey>(
        clazzContentKey = { it.contentKey },
        metadata = metadata {
            put(NavDisplay.TransitionKey) {
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.transitionSpec(this)
                }
            }
            put(NavDisplay.PopTransitionKey) {
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            }
        },
    ) { key ->
        val navigator = LocalNavigator.current
        sharedTransitionScope.DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.switch(
                            from = NavigationBarBSwitchYNavKey,
                            to = NavigationBarBSwitchXNavKey,
                        )
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
internal data object NavigationBarCNavKey : NavKey {
    val contentKey: String get() = this.toString()
}

context(sharedTransitionScope: SharedTransitionScope)
internal fun EntryProviderScope<NavKey>.navigationBarC() {
    entry<NavigationBarCNavKey>(
        clazzContentKey = { it.contentKey },
        metadata = metadata {
            put(NavDisplay.TransitionKey) {
                val initialContentKey = this.initialState.entries.last().contentKey
                val isInitialOuter1 = initialContentKey == Outer1NavKey.contentKey
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    isInitialOuter1 -> ShowcaseAnimations.popTransitionSpec(this)
                    else -> ShowcaseAnimations.transitionSpec(this)
                }
            }
            put(NavDisplay.PopTransitionKey) {
                when {
                    isNavigationBarSwitch() -> ShowcaseAnimations.topLevelTransitionSpec(this)
                    else -> ShowcaseAnimations.popTransitionSpec(this)
                }
            }
        },
    ) { key ->
        val navigator = LocalNavigator.current
        sharedTransitionScope.DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.push(key = Outer1NavKey)
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

context(sharedTransitionScope: SharedTransitionScope)
internal fun EntryProviderScope<NavKey>.outer1() {
    entry<Outer1NavKey>(
        clazzContentKey = { it.contentKey },
        metadata = metadata {
            put(NavDisplay.TransitionKey) {
                val initialContentKey = this.initialState.entries.last().contentKey
                val isInitialOuter2 = initialContentKey == Outer2NavKey.contentKey
                when {
                    isInitialOuter2 -> ShowcaseAnimations.popTransitionSpec(this)
                    else -> ShowcaseAnimations.transitionSpec(this)
                }
            }
        },
    ) { key ->
        val navigator = LocalNavigator.current
        sharedTransitionScope.DemoScaffold(
            key = key,
            buttons = {
                Button(
                    onClick = dropUnlessResumed {
                        navigator.push(key = Outer2NavKey)
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

context(sharedTransitionScope: SharedTransitionScope)
internal fun EntryProviderScope<NavKey>.outer2() {
    entry<Outer2NavKey>(
        clazzContentKey = { it.contentKey },
    ) { key ->
        sharedTransitionScope.DemoScaffold(key = key)
    }
}

private val NavigationBarKeys = setOf(
    NavigationBarANavKey,
    NavigationBarA1NavKey,
    NavigationBarBNavKey,
    NavigationBarBSwitchXNavKey,
    NavigationBarBSwitchYNavKey,
    NavigationBarCNavKey,
)

private enum class NavigationBarItem {
    A,
    B,
    C,
    ;

    companion object {
        fun fromNavKey(key: NavKey): NavigationBarItem? = when (key) {
            NavigationBarANavKey, NavigationBarA1NavKey -> A
            NavigationBarBNavKey, NavigationBarBSwitchXNavKey, NavigationBarBSwitchYNavKey -> B
            NavigationBarCNavKey -> C
            else -> null
        }

        fun fromContentKey(key: String): NavigationBarItem? = when (key) {
            NavigationBarANavKey.contentKey,
            NavigationBarA1NavKey.contentKey,
                -> A

            NavigationBarBNavKey.contentKey,
            NavigationBarBSwitchXNavKey.contentKey,
            NavigationBarBSwitchYNavKey.contentKey,
                -> B

            NavigationBarCNavKey.contentKey,
                -> C

            else -> null
        }
    }
}

private fun Transition.Segment<Scene<*>>.isNavigationBarSwitch(): Boolean {
    val initialContentKey = this.initialState.entries.last().contentKey as? String ?: return false
    val targetContentKey = this.targetState.entries.last().contentKey as? String ?: return false
    val initialItem = NavigationBarItem.fromContentKey(initialContentKey) ?: return false
    val targetItem = NavigationBarItem.fromContentKey(targetContentKey) ?: return false
    return initialItem != targetItem
}

@Composable
private fun SharedTransitionScope.DemoScaffold(
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
            val selected = NavigationBarItem.fromNavKey(key)
            if (selected != null) {
                NavigationBar(
                    windowInsets = WindowInsets(0, 0, 0, 0),
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(
                                key = "NavNode-Demo-NavigationBar",
                            ),
                            animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                        ),
                ) {
                    NavigationBarItem(
                        selected = selected == NavigationBarItem.A,
                        onClick = dropUnlessResumed {
                            navigator.switch(
                                from = NavigationBarKeys,
                                to = listOf(NavigationBarANavKey, NavigationBarA1NavKey),
                            )
                        },
                        icon = { Text("A") }, // TODO
                        modifier = Modifier
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(
                                    key = "NavNode-Demo-NavigationBarItem-A",
                                ),
                                animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                            ),
                    )
                    NavigationBarItem(
                        selected = selected == NavigationBarItem.B,
                        onClick = dropUnlessResumed {
                            navigator.switch(
                                from = NavigationBarKeys,
                                to = listOf(
                                    NavigationBarBNavKey,
                                    NavigationBarBSwitchXNavKey,
                                    NavigationBarBSwitchYNavKey,
                                ),
                            )
                        },
                        icon = { Text("B") }, // TODO
                        modifier = Modifier
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(
                                    key = "NavNode-Demo-NavigationBarItem-B",
                                ),
                                animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                            ),
                    )
                    NavigationBarItem(
                        selected = selected == NavigationBarItem.C,
                        onClick = dropUnlessResumed {
                            navigator.switch(
                                from = NavigationBarKeys,
                                to = NavigationBarCNavKey,
                            )
                        },
                        icon = { Text("C") }, // TODO
                        modifier = Modifier
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(
                                    key = "NavNode-Demo-NavigationBarItem-C",
                                ),
                                animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                            ),
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
