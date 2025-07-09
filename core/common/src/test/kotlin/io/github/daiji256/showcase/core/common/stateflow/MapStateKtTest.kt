package io.github.daiji256.showcase.core.common.stateflow

import app.cash.turbine.test
import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest

class MapStateKtTest {
    @Test
    fun mapState_value() {
        var transformCount = 0
        val source = MutableStateFlow(0.0f)
        val target = source.mapState {
            transformCount++
            it.roundToInt()
        }

        assertEquals(transformCount, 0)
        assertEquals(target.value, 0)
        assertEquals(transformCount, 1)

        source.value = 0.6f
        assertEquals(transformCount, 1)
        assertEquals(target.value, 1)
        assertEquals(transformCount, 2)

        target.value
        target.value
        assertEquals(transformCount, 4)
    }

    @Test
    fun mapState_replayCache() {
        var transformCount = 0
        val source = MutableStateFlow(0.0f)
        val target = source.mapState {
            transformCount++
            it.roundToInt()
        }

        assertEquals(transformCount, 0)
        assertEquals(target.replayCache, listOf(0))
        assertEquals(transformCount, 1)

        source.value = 0.6f
        assertEquals(transformCount, 1)
        assertEquals(target.replayCache, listOf(1))
        assertEquals(transformCount, 2)

        target.replayCache
        target.replayCache
        assertEquals(transformCount, 4)
    }

    @Test
    fun mapState_collect() = runTest {
        var transformCount = 0
        val source = MutableStateFlow(0.0f)
        val target = source.mapState {
            transformCount++
            it.roundToInt()
        }

        target.test {
            assertEquals(transformCount, 1)
            assertEquals(awaitItem(), 0)

            source.value = 0.0f
            assertEquals(transformCount, 1)
            expectNoEvents()

            source.value = 0.3f
            assertEquals(transformCount, 2)
            expectNoEvents()

            source.value = 0.6f
            assertEquals(transformCount, 3)
            assertEquals(awaitItem(), 1)
        }
    }
}
