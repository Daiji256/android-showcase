package io.github.daiji256.showcase.core.designsystem.theme

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigationevent.NavigationEvent

object ShowcaseAnimations {
    val transitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
        ContentTransform(
            targetContentEnter = sharedIn(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
            ),
            initialContentExit = sharedOut(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
            ),
        )
    }

    val popTransitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
        ContentTransform(
            targetContentEnter = sharedIn(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
            ),
            initialContentExit = sharedOut(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
            ),
        )
    }

    val predictivePopTransitionSpec: AnimatedContentTransitionScope<*>.(
        swipeEdge: @NavigationEvent.SwipeEdge Int,
    ) -> ContentTransform = { swipeEdge ->
        ContentTransform(
            targetContentEnter = scaleIn(
                initialScale = 0.875f,
                transformOrigin = TransformOrigin(
                    pivotFractionX = -0.5f,
                    pivotFractionY = 0.5f,
                ),
                animationSpec = tween(
                    durationMillis = PredictivePopTransitionDelayedDuration,
                    delayMillis = PredictivePopTransitionDelay,
                    easing = StandardDecelerateEasing,
                ),
            ),
            initialContentExit = scaleOut(
                targetScale = 0.75f,
                transformOrigin = TransformOrigin(
                    pivotFractionX = when (swipeEdge) {
                        NavigationEvent.EDGE_LEFT -> 0.625f
                        NavigationEvent.EDGE_RIGHT -> 0.375f
                        else -> 0.5f
                    },
                    pivotFractionY = 0.5f,
                ),
                animationSpec = tween(
                    durationMillis = TransitionDuration,
                    easing = StandardAccelerateEasing,
                ),
            ) +
                fadeOut(
                    animationSpec = tween(
                        durationMillis = PredictivePopTransitionDelayedDuration,
                        delayMillis = PredictivePopTransitionDelay,
                        easing = StandardAccelerateEasing,
                    ),
                ),
        )
    }

    val topLevelTransitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
        ContentTransform(
            targetContentEnter = EnterTransition.None,
            initialContentExit = ExitTransition.None,
        )
    }

    val enterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = {
        sharedIn(
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
        )
    }

    val exitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = {
        sharedOut(
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
        )
    }

    val popEnterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = {
        sharedIn(
            towards = AnimatedContentTransitionScope.SlideDirection.End,
        )
    }

    val popExitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = {
        sharedOut(
            towards = AnimatedContentTransitionScope.SlideDirection.End,
        )
    }
}

@Stable
private fun AnimatedContentTransitionScope<*>.sharedIn(
    towards: AnimatedContentTransitionScope.SlideDirection,
): EnterTransition =
    slideIntoContainer(
        towards = towards,
        initialOffset = ::transitionOffset,
        animationSpec = tween(
            durationMillis = TransitionDuration,
            easing = StandardDecelerateEasing,
        ),
    ) +
        fadeIn(
            animationSpec = tween(
                durationMillis = TransitionDuration,
                easing = StandardDecelerateEasing,
            ),
        )

@Stable
private fun AnimatedContentTransitionScope<*>.sharedOut(
    towards: AnimatedContentTransitionScope.SlideDirection,
): ExitTransition =
    slideOutOfContainer(
        towards = towards,
        targetOffset = ::transitionOffset,
        animationSpec = tween(
            durationMillis = TransitionDuration,
            easing = StandardDecelerateEasing,
        ),
    ) +
        fadeOut(
            animationSpec = tween(
                durationMillis = TransitionDuration,
                easing = StandardDecelerateEasing,
            ),
        )

private fun transitionOffset(offsetForFullSlide: Int): Int = offsetForFullSlide / 4
private const val TransitionDuration = 300
private const val PredictivePopTransitionDelay = TransitionDuration * 4 / 5
private const val PredictivePopTransitionDelayedDuration =
    TransitionDuration - PredictivePopTransitionDelay
private val StandardDecelerateEasing = CubicBezierEasing(0f, 0f, 0f, 1f)
private val StandardAccelerateEasing = CubicBezierEasing(0.3f, 0f, 1f, 1f)
