package io.github.daiji256.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseAnimations
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator
import io.github.daiji256.showcase.core.ui.navigation.NavNode
import io.github.daiji256.showcase.core.ui.navigation.rememberNavTreeEntries
import io.github.daiji256.showcase.core.ui.showcase.showcase
import io.github.daiji256.showcase.feature.customtabs.customTabs
import io.github.daiji256.showcase.feature.hiltcomposable.hiltComposable
import io.github.daiji256.showcase.feature.ktlint.ktlint
import io.github.daiji256.showcase.feature.license.license
import io.github.daiji256.showcase.feature.localsnackbarhoststate.localSnackbarHostState
import io.github.daiji256.showcase.feature.navigation2arguments.navigation2Arguments
import io.github.daiji256.showcase.feature.navnode.navNode
import io.github.daiji256.showcase.feature.roborazzi.roborazzi
import io.github.daiji256.showcase.feature.safeurihandler.safeUriHandler
import io.github.daiji256.showcase.feature.systemstyle.systemStyle

@Composable
internal fun MainNavDisplay(
    tree: NavNode<NavKey>,
    modifier: Modifier = Modifier,
) {
    val navigator = LocalNavigator.current
    val entries = rememberNavTreeEntries(
        tree = tree,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            showcase(features = Features)
            customTabs()
            hiltComposable()
            ktlint()
            license()
            localSnackbarHostState()
            navigation2Arguments()
            navNode()
            roborazzi()
            safeUriHandler()
            systemStyle()
        },
    )

    NavDisplay(
        entries = entries,
        onBack = navigator::pop,
        transitionSpec = ShowcaseAnimations.transitionSpec,
        popTransitionSpec = ShowcaseAnimations.popTransitionSpec,
        // TODO: predictivePopTransitionSpec,
        modifier = modifier,
    )
}
