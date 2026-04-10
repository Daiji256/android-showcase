package io.github.daiji256.showcase

import io.github.daiji256.showcase.core.ui.feature.FeatureSummary
import io.github.daiji256.showcase.feature.customtabs.CustomTabsSummary
import io.github.daiji256.showcase.feature.hiltcomposable.HiltComposableSummary
import io.github.daiji256.showcase.feature.ktlint.KtlintSummary
import io.github.daiji256.showcase.feature.license.LicenseSummary
import io.github.daiji256.showcase.feature.localsnackbarhoststate.LocalSnackbarHostStateSummary
import io.github.daiji256.showcase.feature.navigation2arguments.Navigation2ArgumentsSummary
import io.github.daiji256.showcase.feature.navnode.NavNodeSummary
import io.github.daiji256.showcase.feature.pastel.PastelSummary
import io.github.daiji256.showcase.feature.roborazzi.RoborazziSummary
import io.github.daiji256.showcase.feature.safeurihandler.SafeUriHandlerSummary
import io.github.daiji256.showcase.feature.systemstyle.SystemStyleSummary
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal val Features: ImmutableList<FeatureSummary> =
    persistentListOf(
        CustomTabsSummary,
        HiltComposableSummary,
        KtlintSummary,
        LicenseSummary,
        LocalSnackbarHostStateSummary,
        Navigation2ArgumentsSummary,
        NavNodeSummary,
        PastelSummary,
        RoborazziSummary,
        SafeUriHandlerSummary,
        SystemStyleSummary,
    )
