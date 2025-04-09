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

internal interface Child3ArgumentsGetter {
    fun getArgument(): String
}

@Module
@InstallIn(ViewModelComponent::class)
internal object Child3ArgumentsGetterModule {
    @Provides
    fun providesChild3ArgumentsGetter(savedStateHandle: SavedStateHandle): Child3ArgumentsGetter =
        object : Child3ArgumentsGetter {
            private val route = savedStateHandle.toRoute<Child3ScreenRoute>()

            override fun getArgument(): String = route.argument
        }
}
