package io.github.daiji256.showcase

import io.github.daiji256.showcase.core.ui.feature.FeatureSummary
import io.github.daiji256.showcase.feature.customtabs.CustomTabsSummary
import io.github.daiji256.showcase.feature.fallbackfont.FallbackFontSummary
import io.github.daiji256.showcase.feature.ktlint.KtlintSummary
import io.github.daiji256.showcase.feature.license.LicenseSummary
import io.github.daiji256.showcase.feature.localsnackbarhoststate.LocalSnackbarHostStateSummary
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
        NavNodeSummary,
        KtlintSummary,
        LicenseSummary,
        LocalSnackbarHostStateSummary,
        FallbackFontSummary,
        PastelSummary,
        RoborazziSummary,
        SafeUriHandlerSummary,
        SystemStyleSummary,
    )
