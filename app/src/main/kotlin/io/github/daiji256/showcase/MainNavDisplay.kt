package io.github.daiji256.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.github.daiji256.showcase.core.ui.showcase.ShowcaseNavKey
import io.github.daiji256.showcase.core.ui.showcase.showcase
import io.github.daiji256.showcase.feature.customtabs.CustomTabsNavKey
import io.github.daiji256.showcase.feature.customtabs.CustomTabsSummary
import io.github.daiji256.showcase.feature.customtabs.customTabs
import io.github.daiji256.showcase.feature.hiltcomposable.HiltComposableNavKey
import io.github.daiji256.showcase.feature.hiltcomposable.HiltComposableSummary
import io.github.daiji256.showcase.feature.hiltcomposable.hiltComposable
import io.github.daiji256.showcase.feature.ktlint.KtlintNavKey
import io.github.daiji256.showcase.feature.ktlint.KtlintSummary
import io.github.daiji256.showcase.feature.ktlint.ktlint
import io.github.daiji256.showcase.feature.localsnackbarhoststate.LocalSnackbarHostStateNavKey
import io.github.daiji256.showcase.feature.localsnackbarhoststate.LocalSnackbarHostStateSummary
import io.github.daiji256.showcase.feature.localsnackbarhoststate.localSnackbarHostState
import io.github.daiji256.showcase.feature.navigation2arguments.Navigation2ArgumentsNavKey
import io.github.daiji256.showcase.feature.navigation2arguments.Navigation2ArgumentsSummary
import io.github.daiji256.showcase.feature.navigation2arguments.navigation2Arguments
import io.github.daiji256.showcase.feature.roborazzi.RoborazziNavKey
import io.github.daiji256.showcase.feature.roborazzi.RoborazziSummary
import io.github.daiji256.showcase.feature.roborazzi.roborazzi
import io.github.daiji256.showcase.feature.safeurihandler.SafeUriHandlerNavKey
import io.github.daiji256.showcase.feature.safeurihandler.SafeUriHandlerSummary
import io.github.daiji256.showcase.feature.safeurihandler.safeUriHandler
import io.github.daiji256.showcase.feature.systemstyle.SystemStyleNavKey
import io.github.daiji256.showcase.feature.systemstyle.SystemStyleSummary
import io.github.daiji256.showcase.feature.systemstyle.systemStyle
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
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        // TODO: Transition
        modifier = modifier,
        entryProvider = entryProvider {
            showcase(
                features = persistentListOf(
                    CustomTabsSummary(
                        navigate = { backStack.add(CustomTabsNavKey) },
                    ),
                    HiltComposableSummary(
                        navigate = { backStack.add(HiltComposableNavKey) },
                    ),
                    KtlintSummary(
                        navigate = { backStack.add(KtlintNavKey) },
                    ),
                    LocalSnackbarHostStateSummary(
                        navigate = { backStack.add(LocalSnackbarHostStateNavKey) },
                    ),
                    Navigation2ArgumentsSummary(
                        navigate = { backStack.add(Navigation2ArgumentsNavKey) },
                    ),
                    RoborazziSummary(
                        navigate = { backStack.add(RoborazziNavKey) },
                    ),
                    SafeUriHandlerSummary(
                        navigate = { backStack.add(SafeUriHandlerNavKey) },
                    ),
                    SystemStyleSummary(
                        navigate = { backStack.add(SystemStyleNavKey) },
                    ),
                ),
            )
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
