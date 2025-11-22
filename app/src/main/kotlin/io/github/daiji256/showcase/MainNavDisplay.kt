package io.github.daiji256.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.github.daiji256.showcase.core.ui.showcase.Feature
import io.github.daiji256.showcase.core.ui.showcase.ShowcaseNavKey
import io.github.daiji256.showcase.core.ui.showcase.showcase
import io.github.daiji256.showcase.feature.customtabs.CustomTabsNavKey
import io.github.daiji256.showcase.feature.customtabs.customTabs
import io.github.daiji256.showcase.feature.customtabs.customTabsTitle
import io.github.daiji256.showcase.feature.hiltcomposable.HiltComposableNavKey
import io.github.daiji256.showcase.feature.hiltcomposable.hiltComposable
import io.github.daiji256.showcase.feature.hiltcomposable.hiltComposableTitle
import io.github.daiji256.showcase.feature.ktlint.KtlintNavKey
import io.github.daiji256.showcase.feature.ktlint.ktlint
import io.github.daiji256.showcase.feature.ktlint.ktlintTitle
import io.github.daiji256.showcase.feature.localsnackbarhoststate.LocalSnackbarHostStateNavKey
import io.github.daiji256.showcase.feature.localsnackbarhoststate.localSnackbarHostState
import io.github.daiji256.showcase.feature.localsnackbarhoststate.localSnackbarHostStateTitle
import io.github.daiji256.showcase.feature.navigation2arguments.Navigation2ArgumentsNavKey
import io.github.daiji256.showcase.feature.navigation2arguments.navigation2Arguments
import io.github.daiji256.showcase.feature.navigation2arguments.navigation2ArgumentsTitle
import io.github.daiji256.showcase.feature.roborazzi.RoborazziNavKey
import io.github.daiji256.showcase.feature.roborazzi.roborazzi
import io.github.daiji256.showcase.feature.roborazzi.roborazziTitle
import io.github.daiji256.showcase.feature.safeurihandler.SafeUriHandlerNavKey
import io.github.daiji256.showcase.feature.safeurihandler.safeUriHandler
import io.github.daiji256.showcase.feature.safeurihandler.safeUriHandlerTitle
import io.github.daiji256.showcase.feature.systemstyle.SystemStyleNavKey
import io.github.daiji256.showcase.feature.systemstyle.systemStyle
import io.github.daiji256.showcase.feature.systemstyle.systemStyleTitle
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun MainNavDisplay(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(ShowcaseNavKey)
    val navigateUp: () -> Unit = {
        backStack.removeLastOrNull()
        // TODO: Deep links support
    }

    val features = persistentListOf(
        Feature(
            title = customTabsTitle,
            onNavigateClick = { backStack.add(CustomTabsNavKey) },
        ),
        Feature(
            title = hiltComposableTitle,
            onNavigateClick = { backStack.add(HiltComposableNavKey) },
        ),
        Feature(
            title = ktlintTitle,
            onNavigateClick = { backStack.add(KtlintNavKey) },
        ),
        Feature(
            title = localSnackbarHostStateTitle,
            onNavigateClick = { backStack.add(LocalSnackbarHostStateNavKey) },
        ),
        Feature(
            title = navigation2ArgumentsTitle,
            onNavigateClick = { backStack.add(Navigation2ArgumentsNavKey) },
        ),
        Feature(
            title = roborazziTitle,
            onNavigateClick = { backStack.add(RoborazziNavKey) },
        ),
        Feature(
            title = safeUriHandlerTitle,
            onNavigateClick = { backStack.add(SafeUriHandlerNavKey) },
        ),
        Feature(
            title = systemStyleTitle,
            onNavigateClick = { backStack.add(SystemStyleNavKey) },
        ),
    )

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        // TODO: Transition
        modifier = modifier,
        entryProvider = entryProvider {
            showcase(features = features)
            customTabs(onNavigateUpClick = navigateUp)
            hiltComposable(onNavigateUpClick = navigateUp)
            ktlint(onNavigateUpClick = navigateUp)
            localSnackbarHostState(onNavigateUpClick = navigateUp)
            navigation2Arguments(onNavigateUpClick = navigateUp)
            roborazzi(onNavigateUpClick = navigateUp)
            safeUriHandler(onNavigateUpClick = navigateUp)
            systemStyle(onNavigateUpClick = navigateUp)
        },
    )
}
