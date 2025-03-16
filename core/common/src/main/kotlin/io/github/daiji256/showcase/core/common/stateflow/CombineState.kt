package io.github.daiji256.showcase.core.common.stateflow

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Returns a [StateFlow] whose values are generated with [transform] function by combining the
 * values by each input [StateFlow].
 *
 * Note: Since the [transform] function is applied every time the [StateFlow.value] is read, it
 * should be kept lightweight.
 */
fun <T1, T2, R> combineState(
    flow: StateFlow<T1>,
    flow2: StateFlow<T2>,
    transform: (T1, T2) -> R,
): StateFlow<R> =
    combineState(flow, flow2) { args: Array<*> ->
        @Suppress("UNCHECKED_CAST")
        transform(
            args[0] as T1,
            args[1] as T2,
        )
    }

/**
 * Returns a [StateFlow] whose values are generated with [transform] function by combining the
 * values by each input [StateFlow].
 *
 * Note: Since the [transform] function is applied every time the [StateFlow.value] is read, it
 * should be kept lightweight.
 */
fun <T1, T2, T3, R> combineState(
    flow: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    transform: (T1, T2, T3) -> R,
): StateFlow<R> =
    combineState(flow, flow2, flow3) { args: Array<*> ->
        @Suppress("UNCHECKED_CAST")
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
        )
    }

/**
 * Returns a [StateFlow] whose values are generated with [transform] function by combining the
 * values by each input [StateFlow].
 *
 * Note: Since the [transform] function is applied every time the [StateFlow.value] is read, it
 * should be kept lightweight.
 */
fun <T1, T2, T3, T4, R> combineState(
    flow: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    flow4: StateFlow<T4>,
    transform: (T1, T2, T3, T4) -> R,
): StateFlow<R> =
    combineState(flow, flow2, flow3, flow4) { args: Array<*> ->
        @Suppress("UNCHECKED_CAST")
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
        )
    }

/**
 * Returns a [StateFlow] whose values are generated with [transform] function by combining the
 * values by each input [StateFlow].
 *
 * Note: Since the [transform] function is applied every time the [StateFlow.value] is read, it
 * should be kept lightweight.
 */
fun <T1, T2, T3, T4, T5, R> combineState(
    flow: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    flow4: StateFlow<T4>,
    flow5: StateFlow<T5>,
    transform: (T1, T2, T3, T4, T5) -> R,
): StateFlow<R> =
    combineState(flow, flow2, flow3, flow4, flow5) { args: Array<*> ->
        @Suppress("UNCHECKED_CAST")
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
            args[4] as T5,
        )
    }

/**
 * Returns a [StateFlow] whose values are generated with [transform] function by combining the
 * values by each input [StateFlow].
 *
 * Note: Since the [transform] function is applied every time the [StateFlow.value] is read, it
 * should be kept lightweight.
 */
inline fun <reified T, R> combineState(
    vararg flows: StateFlow<T>,
    crossinline transform: (Array<T>) -> R,
): StateFlow<R> {
    @Suppress("UnnecessaryOptInAnnotation")
    @OptIn(ExperimentalForInheritanceCoroutinesApi::class)
    return object : StateFlow<R> {
        override val value: R
            get() = transform(flows.map { it.value }.toTypedArray())

        override val replayCache: List<R>
            get() = listOf(value)

        override suspend fun collect(collector: FlowCollector<R>): Nothing {
            combine(flows = flows, transform = transform).distinctUntilChanged()
                .collect(collector = collector)
            awaitCancellation()
        }
    }
}
