package io.github.daiji256.showcase.feature.hiltcomposable.hilt

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dagger.hilt.EntryPoints
import dagger.hilt.android.EntryPointAccessors

@Composable
inline fun <reified T : Any> rememberComposableEntryPoint(): T {
    val activity = checkNotNull(LocalActivity.current)
    return remember {
        val builderEntryPoint =
            EntryPointAccessors.fromActivity<ComposableComponentBuilderEntryPoint>(activity)
        EntryPoints.get(builderEntryPoint.builder.build(), T::class.java)
    }
}
