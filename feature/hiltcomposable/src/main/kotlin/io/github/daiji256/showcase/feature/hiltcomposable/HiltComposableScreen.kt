package io.github.daiji256.showcase.feature.hiltcomposable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.feature.hiltcomposable.bar.Bar
import io.github.daiji256.showcase.feature.hiltcomposable.bar.rememberBar
import io.github.daiji256.showcase.feature.hiltcomposable.foo.Foo
import io.github.daiji256.showcase.feature.hiltcomposable.foo.rememberFoo

@Composable
internal fun HiltComposableScreen(
    onNavigateUpClick: () -> Unit,
) {
    HiltComposableScreen(
        onNavigateUpClick = onNavigateUpClick,
        foo = rememberFoo(),
        bar = rememberBar(),
    )
}

@Composable
private fun HiltComposableScreen(
    onNavigateUpClick: () -> Unit,
    foo: Foo,
    bar: Bar,
) {
    Document(
        title = stringResource(id = R.string.feature_hilt_comp_title),
        markdown = stringResource(
            id = R.string.feature_hilt_comp_document_md,
            foo.toString(),
            bar.toString(),
        ),
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun HiltComposableScreenPreview() {
    ShowcaseTheme {
        HiltComposableScreen(
            onNavigateUpClick = {},
            foo = Foo(),
            bar = Bar(),
        )
    }
}
