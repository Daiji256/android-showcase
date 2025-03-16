package io.github.daiji256.showcase.core.designsystem.theme

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry

object ShowcaseAnimations {
    val enterTransition:
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition
        @Composable get() {
            val density = LocalDensity.current
            return { sharedIn(forward = true, density = density) }
        }

    val exitTransition:
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
        @Composable get() {
            val density = LocalDensity.current
            return { sharedOut(forward = true, density = density) }
        }

    val popEnterTransition:
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition
        @Composable get() {
            val density = LocalDensity.current
            return { sharedIn(forward = false, density = density) }
        }

    val popExitTransition:
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
        @Composable get() {
            val density = LocalDensity.current
            return { sharedOut(forward = false, density = density) }
        }
}

@Stable
private fun sharedIn(forward: Boolean, density: Density): EnterTransition =
    slideInHorizontally(
        animationSpec = tween(
            durationMillis = DurationMillis,
            easing = FastOutSlowInEasing,
        ),
        initialOffsetX = {
            val slideDistance = with(density) { SlideDistanceDp.roundToPx() }
            if (forward) slideDistance else -slideDistance
        },
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = IncomingDurationMillis,
            delayMillis = OutgoingDurationMillis,
            easing = LinearOutSlowInEasing,
        ),
    )

@Stable
private fun sharedOut(forward: Boolean, density: Density): ExitTransition =
    slideOutHorizontally(
        animationSpec = tween(
            durationMillis = DurationMillis,
            easing = FastOutSlowInEasing,
        ),
        targetOffsetX = {
            val slideDistance = with(density) { SlideDistanceDp.roundToPx() }
            if (forward) -slideDistance else slideDistance
        },
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = OutgoingDurationMillis,
            delayMillis = 0,
            easing = FastOutLinearInEasing,
        ),
    )

private val SlideDistanceDp = 30.dp
private const val ProgressThreshold = 0.35f
private const val DurationMillis = 300
private const val OutgoingDurationMillis = (DurationMillis * ProgressThreshold).toInt()
private const val IncomingDurationMillis = DurationMillis - OutgoingDurationMillis
