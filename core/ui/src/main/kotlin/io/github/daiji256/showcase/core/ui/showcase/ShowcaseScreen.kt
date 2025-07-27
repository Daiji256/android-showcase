package io.github.daiji256.showcase.core.ui.showcase

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.R
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ShowcaseScreen(
    features: ImmutableList<FeatureSummary>,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.core_ui_showcase_title),
                    )
                },
            )
        },
    ) { padding ->
        val layoutDirection = LocalLayoutDirection.current
        LazyColumn(
            contentPadding = PaddingValues(
                bottom = padding.calculateBottomPadding(),
            ),
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    start = padding.calculateStartPadding(layoutDirection),
                    end = padding.calculateEndPadding(layoutDirection),
                ),
        ) {
            items(items = features) { feature ->
                FeatureItem(
                    feature = feature,
                    modifier = Modifier.fillMaxWidth(),
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun FeatureItem(
    feature: FeatureSummary,
    modifier: Modifier = Modifier,
) {
    Text(
        text = feature.title,
        modifier = modifier
            .clickable(onClick = dropUnlessResumed { feature.navigate() })
            .padding(12.dp),
    )
}

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(fontScale = 0.85f, showSystemUi = true)
@Preview(fontScale = 2.0f, showSystemUi = true)
@Composable
private fun ShowcaseScreenPreview() {
    ShowcaseTheme {
        val features = remember {
            List(20) {
                object : FeatureSummary {
                    override val title: String
                        @Composable get() = "Feature$it"

                    override fun navigate() {}
                }
            }.toImmutableList()
        }
        ShowcaseScreen(features = features)
    }
}
