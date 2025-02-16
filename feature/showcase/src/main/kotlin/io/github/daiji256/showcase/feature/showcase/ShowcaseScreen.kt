package io.github.daiji256.showcase.feature.showcase

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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary
import io.github.daiji256.showcase.core.ui.navigation.LocalNavController
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
                        text = stringResource(id = R.string.feature_showcase_title),
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
    val navController = LocalNavController.current
    Text(
        text = feature.title(),
        modifier = modifier
            .clickable(
                onClick = dropUnlessResumed {
                    with(feature) { navController.navigate() }
                },
            )
            .padding(12.dp),
    )
}

@Preview
@Composable
private fun ShowcaseScreenPreview() {
    // TODO: Theme
    val features = remember {
        List(20) {
            object : FeatureSummary {
                @Composable
                override fun title(): String = "Feature$it"
                override fun NavController.navigate() {}
            }
        }.toImmutableList()
    }
    CompositionLocalProvider(
        LocalNavController provides rememberNavController(),
    ) {
        ShowcaseScreen(features = features)
    }
}
