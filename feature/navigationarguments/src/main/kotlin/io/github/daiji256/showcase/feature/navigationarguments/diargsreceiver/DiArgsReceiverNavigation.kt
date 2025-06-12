package io.github.daiji256.showcase.feature.navigationarguments.diargsreceiver

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.serialization.Serializable

@Serializable
internal data class DiArgsReceiverScreenRoute(val arg: String)

internal fun NavGraphBuilder.diArgsReceiverScreen(navController: NavController) {
    composable<DiArgsReceiverScreenRoute> {
        DiArgsReceiverScreen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

internal fun NavController.navigateToDiArgsReceiverScreen(arg: String) =
    navigate(route = DiArgsReceiverScreenRoute(arg = arg))

@Module
@InstallIn(ViewModelComponent::class)
internal object DiArgsReceiverScreenRouteModule {
    @Provides
    fun providesDiArgsReceiverScreenRoute(
        savedStateHandle: SavedStateHandle,
    ): DiArgsReceiverScreenRoute =
        savedStateHandle.toRoute<DiArgsReceiverScreenRoute>()
}
