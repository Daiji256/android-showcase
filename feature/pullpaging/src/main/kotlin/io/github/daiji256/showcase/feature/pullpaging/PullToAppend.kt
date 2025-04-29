package io.github.daiji256.showcase.feature.pullpaging

import androidx.annotation.FloatRange
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.layout.layout
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

fun Modifier.pullToAppend(
    isAppending: Boolean,
    state: PullToAppendState,
    enabled: Boolean = true,
    threshold: Dp = DefaultPositionalThreshold,
    onAppend: () -> Unit,
): Modifier = this then PullToAppendElement(
    state = state,
    isAppending = isAppending,
    enabled = enabled,
    onAppend = onAppend,
    threshold = threshold,
)

private data class PullToAppendElement(
    val isAppending: Boolean,
    val onAppend: () -> Unit,
    val enabled: Boolean,
    val state: PullToAppendState,
    val threshold: Dp,
) : ModifierNodeElement<PullToAppendModifierNode>() {
    override fun create() =
        PullToAppendModifierNode(
            isAppending = isAppending,
            onAppend = onAppend,
            enabled = enabled,
            state = state,
            threshold = threshold,
        )

    override fun update(node: PullToAppendModifierNode) {
        node.onAppend = onAppend
        node.enabled = enabled
        node.state = state
        node.threshold = threshold
        if (node.isAppending != isAppending) {
            node.isAppending = isAppending
            node.update()
        }
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "PullToAppendModifierNode"
        properties["isAppending"] = isAppending
        properties["onAppend"] = onAppend
        properties["enabled"] = enabled
        properties["state"] = state
        properties["threshold"] = threshold
    }
}

private class PullToAppendModifierNode(
    var isAppending: Boolean,
    var onAppend: () -> Unit,
    var enabled: Boolean,
    var state: PullToAppendState,
    var threshold: Dp,
) : DelegatingNode(), CompositionLocalConsumerModifierNode, NestedScrollConnection {
    private var nestedScrollNode: DelegatableNode =
        nestedScrollModifierNode(connection = this, dispatcher = null)

    private var verticalOffset by mutableFloatStateOf(0f)
    private var distancePulled by mutableFloatStateOf(0f)

    private val adjustedDistancePulled: Float
        get() = distancePulled * DragMultiplier

    private val negativeThresholdPx
        get() = with(currentValueOf(LocalDensity)) { -threshold.roundToPx() }

    private val progress
        get() = adjustedDistancePulled / negativeThresholdPx

    override fun onAttach() {
        delegate(nestedScrollNode)
        coroutineScope.launch {
            state.snapTo(if (isAppending) 1f else 0f)
        }
    }

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset =
        when {
            state.isAnimating -> Offset.Zero

            !enabled -> Offset.Zero

            // Swiping up
            source == NestedScrollSource.UserInput && available.y > 0 -> {
                consumeAvailableOffset(available)
            }

            else -> Offset.Zero
        }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource,
    ): Offset =
        when {
            state.isAnimating -> Offset.Zero

            !enabled -> Offset.Zero

            // Swiping down
            source == NestedScrollSource.UserInput -> {
                val newOffset = consumeAvailableOffset(available)
                coroutineScope.launch { state.snapTo(verticalOffset / negativeThresholdPx) }
                newOffset
            }

            else -> Offset.Zero
        }

    override suspend fun onPreFling(available: Velocity): Velocity =
        Velocity(x = 0f, y = onRelease(available.y))

    fun update() {
        coroutineScope.launch {
            if (!isAppending) {
                animateToHidden()
            } else {
                animateToThreshold()
            }
        }
    }

    private fun consumeAvailableOffset(available: Offset): Offset =
        Offset(
            x = 0f,
            y = if (isAppending) {
                0f
            } else {
                val newOffset = (distancePulled + available.y).coerceAtMost(0f)
                val dragConsumed = newOffset - distancePulled
                distancePulled = newOffset
                verticalOffset = calculateVerticalOffset()
                dragConsumed
            },
        )

    private suspend fun onRelease(velocity: Float): Float {
        if (isAppending) return 0f
        if (adjustedDistancePulled < negativeThresholdPx) {
            animateToThreshold()
            onAppend()
        } else {
            animateToHidden()
        }

        val consumed = when {
            distancePulled == 0f -> 0f
            velocity > 0f -> 0f
            else -> velocity
        }
        distancePulled = 0f
        return consumed
    }

    private fun calculateVerticalOffset(): Float =
        when {
            adjustedDistancePulled >= negativeThresholdPx -> adjustedDistancePulled

            else -> {
                val overshootPercent = abs(progress) - 1.0f
                val linearTension = overshootPercent.coerceIn(0f, 0.5f)
                val tensionPercent = linearTension - linearTension.pow(2) / 4
                val extraOffset = negativeThresholdPx * tensionPercent
                negativeThresholdPx + extraOffset
            }
        }

    private suspend fun animateToThreshold() {
        state.animateToThreshold()
        distancePulled = negativeThresholdPx.toFloat()
        verticalOffset = negativeThresholdPx.toFloat()
    }

    private suspend fun animateToHidden() {
        state.animateToHidden()
        distancePulled = 0f
        verticalOffset = 0f
    }
}

@Stable
interface PullToAppendState {
    @get:FloatRange(from = 0.0)
    val distanceFraction: Float

    val isAnimating: Boolean

    suspend fun animateToThreshold()

    suspend fun animateToHidden()

    suspend fun snapTo(@FloatRange(from = 0.0) targetValue: Float)
}

@Composable
fun rememberPullToAppendState(): PullToAppendState =
    rememberSaveable(saver = PullToAppendStateImpl.Saver) {
        PullToAppendStateImpl()
    }

private class PullToAppendStateImpl(
    private val anim: Animatable<Float, AnimationVector1D>,
) : PullToAppendState {
    constructor() : this(Animatable(0f, Float.VectorConverter))

    override val distanceFraction
        get() = anim.value

    override val isAnimating: Boolean
        get() = anim.isRunning

    override suspend fun animateToThreshold() {
        anim.animateTo(1f)
    }

    override suspend fun animateToHidden() {
        anim.animateTo(0f)
    }

    override suspend fun snapTo(targetValue: Float) {
        anim.snapTo(targetValue)
    }

    companion object {
        val Saver = Saver<PullToAppendStateImpl, Float>(
            save = { it.anim.value },
            restore = { PullToAppendStateImpl(anim = Animatable(it, Float.VectorConverter)) },
        )
    }
}

@Composable
fun AppendIndicator(
    isAppending: Boolean,
    isReached: Boolean,
    pullToAppendState: PullToAppendState,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    Box(modifier = modifier) {
        Crossfade(
            targetState = Pair(isAppending, isReached),
            animationSpec = IndicatorCrossfadeAnimationSpec,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    layout(
                        width = placeable.width,
                        height = max(
                            (placeable.height * pullToAppendState.distanceFraction).roundToInt(),
                            IndicatorInitialHeight.roundToPx(),
                        ),
                    ) {
                        placeable.placeRelative(x = 0, y = 0)
                    }
                },
        ) { (isAppending, isReached) ->
            when {
                isReached ->
                    Box(
                        modifier = Modifier
                            .padding(vertical = ReachedDotVerticalPadding)
                            .size(ReachedDotSize)
                            .background(color = color, shape = CircleShape),
                    )

                isAppending ->
                    CircularProgressIndicator(
                        strokeWidth = CircularIndicatorStrokeWidth,
                        color = color,
                        modifier = Modifier
                            .padding(vertical = CircularIndicatorVerticalPadding)
                            .size(CircularIndicatorSize),
                    )

                else ->
                    // TODO: Consider implementing a custom indicator
                    CircularProgressIndicator(
                        progress = { pullToAppendState.distanceFraction },
                        strokeWidth = CircularIndicatorStrokeWidth,
                        color = color,
                        trackColor = Color.Transparent,
                        modifier = Modifier
                            .padding(vertical = CircularIndicatorVerticalPadding)
                            .size(CircularIndicatorSize)
                            .graphicsLayer {
                                rotationZ = pullToAppendState.distanceFraction * 360
                                alpha = pullToAppendState.distanceFraction
                            },
                    )
            }
        }
    }
}

private val DefaultPositionalThreshold = 80.dp
private val IndicatorCrossfadeAnimationSpec = tween<Float>(durationMillis = 100)
private val IndicatorInitialHeight = 32.dp
private val CircularIndicatorStrokeWidth = 2.5.dp
private val CircularIndicatorSize = 16.dp
private val CircularIndicatorVerticalPadding = 32.dp
private val ReachedDotSize = 4.dp
private val ReachedDotVerticalPadding = 22.dp
private const val DragMultiplier = 0.5f
