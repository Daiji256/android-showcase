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

internal interface DiArgsReceiverArgs {
    val arg: String
}

@Module
@InstallIn(ViewModelComponent::class)
internal object DiArgsReceiverArgsModule {
    @Provides
    fun providesDiArgsReceiverArgs(savedStateHandle: SavedStateHandle): DiArgsReceiverArgs =
        object : DiArgsReceiverArgs {
            private val route = savedStateHandle.toRoute<DiArgsReceiverScreenRoute>()

            override val arg: String = route.arg
        }
}
