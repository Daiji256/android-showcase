package io.github.daiji256.showcase.feature.fallbackfont

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val FallbackFontSummary = object : FeatureSummary {
    override val navKey: NavKey = FallbackFontNavKey

    override val title: String
        @Composable get() = stringResource(id = R.string.feature_fallback_font_title)
}
