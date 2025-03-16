package io.github.daiji256.showcase.core.common.stateflow

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

/**
 * Returns a [StateFlow] containing the results of applying the given [transform] function to each
 * value of the original [StateFlow].
 *
 * Note: Since the [transform] function is applied every time the [StateFlow.value] is read, it
 * should be kept lightweight.
 */
inline fun <T, R> StateFlow<T>.mapState(crossinline transform: (T) -> R): StateFlow<R> {
    val source = this

    @Suppress("UnnecessaryOptInAnnotation")
    @OptIn(ExperimentalForInheritanceCoroutinesApi::class)
    return object : StateFlow<R> {
        override val value: R
            get() = transform(source.value)

        override val replayCache: List<R>
            get() = listOf(value)

        override suspend fun collect(collector: FlowCollector<R>): Nothing {
            source.map(transform = transform).distinctUntilChanged().collect(collector = collector)
            awaitCancellation()
        }
    }
}
