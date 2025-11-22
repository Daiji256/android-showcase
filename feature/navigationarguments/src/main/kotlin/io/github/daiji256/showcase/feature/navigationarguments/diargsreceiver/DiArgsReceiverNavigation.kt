package io.github.daiji256.showcase.feature.navigationarguments.diargsreceiver

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.serialization.Serializable

@Serializable
internal data class DiArgsReceiverRoute(val arg: String)

internal fun NavGraphBuilder.diArgsReceiver(
    onNavigateUpClick: () -> Unit,
) {
    composable<DiArgsReceiverRoute> {
        DiArgsReceiverScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}

@Module
@InstallIn(ViewModelComponent::class)
internal object DiArgsReceiverRouteModule {
    @Provides
    fun providesDiArgsReceiverRoute(
        savedStateHandle: SavedStateHandle,
    ): DiArgsReceiverRoute =
        savedStateHandle.toRoute<DiArgsReceiverRoute>()
}
