package io.github.daiji256.showcase.feature.hiltcomposable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
        onNavigateUpClick = onNavigateUpClick,
    ) {
        Text(text = foo.name)
        Text(text = bar.name)
    }
}

@Preview
@Composable
private fun HiltComposableScreenPreview() {
    // TODO: Theme
    HiltComposableScreen(
        onNavigateUpClick = {},
        foo = Foo(name = "Foo"),
        bar = Bar(name = "Bar"),
    )
}
