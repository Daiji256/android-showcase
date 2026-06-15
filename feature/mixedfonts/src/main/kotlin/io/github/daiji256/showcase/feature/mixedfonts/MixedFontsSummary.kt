package io.github.daiji256.showcase.feature.mixedfonts

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val MixedFontsSummary = object : FeatureSummary {
    override val navKey: NavKey = MixedFontsNavKey

    override val title: String
        @Composable get() = stringResource(id = R.string.feature_mixed_fonts_title)
}
