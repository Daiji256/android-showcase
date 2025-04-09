package io.github.daiji256.showcase.feature.navigationarguments.child3

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
internal data class Child3ScreenRoute(val argument: String)

internal fun NavGraphBuilder.child3Screen(navController: NavController) {
    composable<Child3ScreenRoute> {
        Child3Screen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

internal fun NavController.navigateToChild3Screen(argument: String) =
    navigate(route = Child3ScreenRoute(argument = argument))

internal interface Child3Arguments {
    val argument: String
}

@Module
@InstallIn(ViewModelComponent::class)
internal object Child3ArgumentsModule {
    @Provides
    fun providesChild3Arguments(savedStateHandle: SavedStateHandle): Child3Arguments =
        object : Child3Arguments {
            private val route = savedStateHandle.toRoute<Child3ScreenRoute>()

            override val argument: String = route.argument
        }
}
