package io.github.daiji256.showcase.core.common.stateflow

import app.cash.turbine.test
import kotlin.math.roundToInt
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CombineStateKtTest {
    @Test
    fun combine_2() {
        val flow1 = MutableStateFlow(1)
        val flow2 = MutableStateFlow(2.0f)
        val target = combineState(
            flow1,
            flow2,
        ) { value1, value2 ->
            "$value1, $value2"
        }

        assertEquals(target.value, "1, 2.0")
    }

    @Test
    fun combine_3() {
        val flow1 = MutableStateFlow(1)
        val flow2 = MutableStateFlow(2.0f)
        val flow3 = MutableStateFlow(3.0)
        val target = combineState(
            flow1,
            flow2,
            flow3,
        ) { value1, value2, value3 ->
            "$value1, $value2, $value3"
        }

        assertEquals(target.value, "1, 2.0, 3.0")
    }

    @Test
    fun combine_4() {
        val flow1 = MutableStateFlow(1)
        val flow2 = MutableStateFlow(2.0f)
        val flow3 = MutableStateFlow(3.0)
        val flow4 = MutableStateFlow('4')
        val target = combineState(
            flow1,
            flow2,
            flow3,
            flow4,
        ) { value1, value2, value3, value4 ->
            "$value1, $value2, $value3, $value4"
        }

        assertEquals(target.value, "1, 2.0, 3.0, 4")
    }

    @Test
    fun combine_5() {
        val flow1 = MutableStateFlow(1)
        val flow2 = MutableStateFlow(2.0f)
        val flow3 = MutableStateFlow(3.0)
        val flow4 = MutableStateFlow('4')
        val flow5 = MutableStateFlow("5")
        val target = combineState(
            flow1,
            flow2,
            flow3,
            flow4,
            flow5,
        ) { value1, value2, value3, value4, value5 ->
            "$value1, $value2, $value3, $value4, $value5"
        }

        assertEquals(target.value, "1, 2.0, 3.0, 4, 5")
    }

    @Test
    fun combine_vararg() {
        val flow1 = MutableStateFlow(1)
        val flow2 = MutableStateFlow(2.0f)
        val target = combineState(
            flow1,
            flow2,
        ) { values ->
            "${values[0]}, ${values[1]}"
        }

        assertEquals(target.value, "1, 2.0")
    }

    @Test
    fun combine_value() {
        var transformCount = 0
        val flow1 = MutableStateFlow(0.0f)
        val flow2 = MutableStateFlow(0.0f)
        val target = combineState(flow1, flow2) { value1, value2 ->
            transformCount++
            (value1 + value2).roundToInt()
        }

        assertEquals(transformCount, 0)
        assertEquals(target.value, 0)
        assertEquals(transformCount, 1)

        flow1.value = 0.3f
        assertEquals(transformCount, 1)
        assertEquals(target.value, 0)
        assertEquals(transformCount, 2)

        flow2.value = 0.3f
        assertEquals(transformCount, 2)
        assertEquals(target.value, 1)
        assertEquals(transformCount, 3)

        target.value
        target.value
        assertEquals(transformCount, 5)
    }

    @Test
    fun combine_replayCache() {
        var transformCount = 0
        val flow1 = MutableStateFlow(0.0f)
        val flow2 = MutableStateFlow(0.0f)
        val target = combineState(flow1, flow2) { value1, value2 ->
            transformCount++
            (value1 + value2).roundToInt()
        }

        assertEquals(transformCount, 0)
        assertEquals(target.replayCache, listOf(0))
        assertEquals(transformCount, 1)

        flow1.value = 0.3f
        assertEquals(transformCount, 1)
        assertEquals(target.replayCache, listOf(0))
        assertEquals(transformCount, 2)

        flow2.value = 0.3f
        assertEquals(transformCount, 2)
        assertEquals(target.replayCache, listOf(1))
        assertEquals(transformCount, 3)

        target.replayCache
        target.replayCache
        assertEquals(transformCount, 5)
    }

    @Test
    fun combine_collect() = runTest {
        var transformCount = 0
        val flow1 = MutableStateFlow(0.0f)
        val flow2 = MutableStateFlow(0.0f)
        val target = combineState(flow1, flow2) { value1, value2 ->
            transformCount++
            (value1 + value2).roundToInt()
        }

        target.test {
            assertEquals(transformCount, 1)
            assertEquals(awaitItem(), 0)

            flow1.value = 0.0f
            assertEquals(transformCount, 1)
            expectNoEvents()

            flow1.value = 0.3f
            assertEquals(transformCount, 2)
            expectNoEvents()

            flow2.value = 0.6f
            assertEquals(transformCount, 3)
            assertEquals(awaitItem(), 1)
        }
    }
}
