package io.github.daiji256.showcase.feature.hiltcomposable.foo

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FooEntryPoint {
    val foo: Foo
}
