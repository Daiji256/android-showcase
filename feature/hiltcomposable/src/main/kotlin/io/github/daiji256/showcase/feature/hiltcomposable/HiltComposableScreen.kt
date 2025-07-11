package io.github.daiji256.showcase.feature.hiltcomposable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.feature.hiltcomposable.foo.Foo
import io.github.daiji256.showcase.feature.hiltcomposable.foo.rememberFoo

@Composable
internal fun HiltComposableScreen(
    onNavigateUpClick: () -> Unit,
) {
    HiltComposableScreen(
        onNavigateUpClick = onNavigateUpClick,
        foo = rememberFoo(),
    )
}

@Composable
internal fun HiltComposableScreen(
    onNavigateUpClick: () -> Unit,
    foo: Foo,
) {
    Document(
        title = stringResource(id = R.string.feature_hilt_comp_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        Text(text = foo.name)
    }
}

@Preview
@Composable
private fun HiltComposableScreenPreview() {
    // TODO: Theme
    HiltComposableScreen(
        onNavigateUpClick = {},
        foo = Foo(name = "Foo"),
    )
}
