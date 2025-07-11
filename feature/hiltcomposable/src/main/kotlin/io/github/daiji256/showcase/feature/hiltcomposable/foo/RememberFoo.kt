package io.github.daiji256.showcase.feature.hiltcomposable.foo

import androidx.compose.runtime.Composable
import io.github.daiji256.showcase.feature.hiltcomposable.hilt.rememberSingletonEntryPoint

@Composable
internal fun rememberFoo(): Foo = rememberSingletonEntryPoint<FooEntryPoint>().foo
