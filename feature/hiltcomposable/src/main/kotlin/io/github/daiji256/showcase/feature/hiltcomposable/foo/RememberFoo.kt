package io.github.daiji256.showcase.feature.hiltcomposable.foo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.EntryPointAccessors

@Composable
internal fun rememberFoo(): Foo {
    val context = LocalContext.current
    return remember {
        EntryPointAccessors.fromApplication<FooEntryPoint>(context).foo
    }
}
