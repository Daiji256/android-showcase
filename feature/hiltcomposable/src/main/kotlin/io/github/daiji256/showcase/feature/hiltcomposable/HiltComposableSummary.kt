package io.github.daiji256.showcase.feature.hiltcomposable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

@Stable
class HiltComposableSummary(
    private val navigate: () -> Unit,
) : FeatureSummary {
    override val title: String
        @Composable get() = stringResource(id = R.string.feature_hilt_comp_title)

    override fun navigate() = navigate.invoke()
}
