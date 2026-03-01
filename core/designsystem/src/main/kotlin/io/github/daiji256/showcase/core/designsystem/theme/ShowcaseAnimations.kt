package io.github.daiji256.showcase.core.designsystem.theme

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
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
import androidx.compose.runtime.Stable

object ShowcaseAnimations {
    val transitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
        ContentTransform(
            targetContentEnter = sharedIn(forward = true),
            initialContentExit = sharedOut(forward = true),
        )
    }

    val popTransitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
        ContentTransform(
            targetContentEnter = sharedIn(forward = false),
            initialContentExit = sharedOut(forward = false),
        )
    }

    val enterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = {
        sharedIn(forward = true)
    }

    val exitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = {
        sharedOut(forward = true)
    }

    val popEnterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = {
        sharedIn(forward = false)
    }

    val popExitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = {
        sharedOut(forward = false)
    }
}

@Stable
private fun sharedIn(forward: Boolean): EnterTransition =
    slideInHorizontally(
        animationSpec = tween(
            durationMillis = DurationMillis,
            easing = FastOutSlowInEasing,
        ),
        initialOffsetX = { fullWidth ->
            slideDistance(fullWidth) * if (forward) 1 else -1
        },
    ) +
        fadeIn(
            animationSpec = tween(
                durationMillis = IncomingDurationMillis,
                delayMillis = OutgoingDurationMillis,
                easing = LinearOutSlowInEasing,
            ),
        )

@Stable
private fun sharedOut(forward: Boolean): ExitTransition =
    slideOutHorizontally(
        animationSpec = tween(
            durationMillis = DurationMillis,
            easing = FastOutSlowInEasing,
        ),
        targetOffsetX = { fullWidth ->
            slideDistance(fullWidth) * if (forward) -1 else 1
        },
    ) +
        fadeOut(
            animationSpec = tween(
                durationMillis = OutgoingDurationMillis,
                delayMillis = 0,
                easing = FastOutLinearInEasing,
            ),
        )

private fun slideDistance(fullWidth: Int): Int = fullWidth / 8
private const val ProgressThreshold = 0.35f
private const val DurationMillis = 300
private const val OutgoingDurationMillis = (DurationMillis * ProgressThreshold).toInt()
private const val IncomingDurationMillis = DurationMillis - OutgoingDurationMillis
