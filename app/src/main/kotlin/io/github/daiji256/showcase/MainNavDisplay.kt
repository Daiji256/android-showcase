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
import io.github.daiji256.showcase.feature.customtabs.customTabsSummary
import io.github.daiji256.showcase.feature.hiltcomposable.hiltComposable
import io.github.daiji256.showcase.feature.hiltcomposable.hiltComposableSummary
import io.github.daiji256.showcase.feature.ktlint.ktlint
import io.github.daiji256.showcase.feature.ktlint.ktlintSummary
import io.github.daiji256.showcase.feature.license.license
import io.github.daiji256.showcase.feature.license.licenseSummary
import io.github.daiji256.showcase.feature.localsnackbarhoststate.localSnackbarHostState
import io.github.daiji256.showcase.feature.localsnackbarhoststate.localSnackbarHostStateSummary
import io.github.daiji256.showcase.feature.navigation2arguments.navigation2Arguments
import io.github.daiji256.showcase.feature.navigation2arguments.navigation2ArgumentsSummary
import io.github.daiji256.showcase.feature.navnode.navNode
import io.github.daiji256.showcase.feature.navnode.navNodeSummary
import io.github.daiji256.showcase.feature.roborazzi.roborazzi
import io.github.daiji256.showcase.feature.roborazzi.roborazziSummary
import io.github.daiji256.showcase.feature.safeurihandler.safeUriHandler
import io.github.daiji256.showcase.feature.safeurihandler.safeUriHandlerSummary
import io.github.daiji256.showcase.feature.systemstyle.systemStyle
import io.github.daiji256.showcase.feature.systemstyle.systemStyleSummary
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun MainNavDisplay(
    tree: NavNode<NavKey>,
    modifier: Modifier = Modifier,
) {
    val navigator = LocalNavigator.current
    val features = persistentListOf(
        customTabsSummary,
        hiltComposableSummary,
        ktlintSummary,
        licenseSummary,
        localSnackbarHostStateSummary,
        navigation2ArgumentsSummary,
        navNodeSummary,
        roborazziSummary,
        safeUriHandlerSummary,
        systemStyleSummary,
    )
    val entries = rememberNavTreeEntries(
        tree = tree,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            showcase(features = features)
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
        transitionSpec = ShowcaseAnimations.transitionSpec(),
        popTransitionSpec = ShowcaseAnimations.popTransitionSpec(),
        // TODO: predictivePopTransitionSpec,
        modifier = modifier,
    )
}
