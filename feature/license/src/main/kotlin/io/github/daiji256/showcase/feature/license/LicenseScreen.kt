package io.github.daiji256.showcase.feature.license

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.minus
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.component.NavigateUpButton
import io.github.daiji256.showcase.core.ui.navigation.LocalNavigator
import io.github.daiji256.showcase.core.ui.window.LocalWindowShape

@Composable
internal fun LicenseScreen() {
    val navigator = LocalNavigator.current
    val uriHandler = LocalUriHandler.current
    val resources = LocalResources.current
    val dependencies by produceState(initialValue = listOf(), key1 = resources) {
        value = resources.getDependencies()
    }
    LicenseScreen(
        dependencies = dependencies,
        onNavigateUpClick = navigator::navigateUp,
        onUriClick = uriHandler::openUri,
    )
}

@Composable
private fun LicenseScreen(
    dependencies: List<Dependency>,
    onNavigateUpClick: () -> Unit,
    onUriClick: (String) -> Unit,
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.feature_license_title),
                    )
                },
                navigationIcon = {
                    NavigateUpButton(
                        onClick = onNavigateUpClick,
                    )
                },
                scrollBehavior = topAppBarScrollBehavior,
            )
        },
        modifier = Modifier
            .clip(shape = LocalWindowShape.current)
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
    ) { padding ->
        val sortedDependencies by produceState(initialValue = listOf(), key1 = dependencies) {
            value = dependencies
                .groupBy { it.author }
                .mapValues { (_, deps) -> deps.sortedBy { it.name } }
                .toList()
                .sortedByDescending { it.second.size }
        }
        val paddingOnlyB = remember(padding) {
            object : PaddingValues {
                override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp = 0.dp
                override fun calculateTopPadding(): Dp = 0.dp
                override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp = 0.dp
                override fun calculateBottomPadding(): Dp = padding.calculateBottomPadding()
            }
        }
        val paddingOnlyLtr = padding - paddingOnlyB
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp) + paddingOnlyB,
            modifier = Modifier
                .padding(paddingOnlyLtr)
                .fillMaxSize(),
        ) {
            sortedDependencies.forEach { (author, deps) ->
                item(key = "author_$author") {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp, top = 24.dp, end = 8.dp),
                    ) {
                        Text(
                            text = author,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }

                itemsIndexed(
                    items = deps,
                    key = { _, dependency -> "dependency_${dependency.id}" },
                ) { index, dependency ->
                    DependencyItem(
                        isFirst = index == 0,
                        isLast = index == deps.lastIndex,
                        dependency = dependency,
                        onClick = { onUriClick(dependency.uri) },
                        onLicenseClick = { onUriClick(dependency.licenseUri) },
                        modifier = Modifier
                            .padding(top = if (index == 0) 8.dp else 2.dp)
                            .fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Composable
private fun DependencyItem(
    isFirst: Boolean,
    isLast: Boolean,
    dependency: Dependency,
    onClick: () -> Unit,
    onLicenseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        shape = dependencyItemShape(isFirst = isFirst, isLast = isLast),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        modifier = modifier,
    ) {
        Column {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            ) {
                Text(
                    text = dependency.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            CompositionLocalProvider(
                LocalMinimumInteractiveComponentSize provides 32.dp,
            ) {
                Surface(
                    onClick = onLicenseClick,
                    shape = ButtonDefaults.textShape,
                    color = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp, bottom = 10.dp)
                        .semantics { role = Role.Button },
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .widthIn(min = ButtonDefaults.MinWidth)
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                    ) {
                        Text(
                            text = dependency.licenseName,
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun dependencyItemShape(isFirst: Boolean, isLast: Boolean): Shape {
    val ss = MaterialTheme.shapes.extraSmall
    val ls = MaterialTheme.shapes.large
    return RoundedCornerShape(
        topStart = if (isFirst) ls.topStart else ss.topStart,
        topEnd = if (isFirst) ls.topEnd else ss.topEnd,
        bottomStart = if (isLast) ls.bottomStart else ss.bottomStart,
        bottomEnd = if (isLast) ls.bottomEnd else ss.bottomEnd,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun LicenseScreenPreview() {
    ShowcaseTheme {
        LicenseScreen(
            dependencies = listOf(
                Dependency(
                    id = "b1",
                    name = "Library B1",
                    uri = "https://example.com/",
                    author = "Author B",
                    licenseName = "License",
                    licenseUri = "https://example.com/",
                ),
                Dependency(
                    id = "a2",
                    name = "Library A2",
                    uri = "https://example.com/",
                    author = "Author A",
                    licenseName = "License",
                    licenseUri = "https://example.com/",
                ),
                Dependency(
                    id = "a1",
                    name = "Library A1",
                    uri = "https://example.com/",
                    author = "Author A",
                    licenseName = "License",
                    licenseUri = "https://example.com/",
                ),
            ),
            onNavigateUpClick = {},
            onUriClick = {},
        )
    }
}
