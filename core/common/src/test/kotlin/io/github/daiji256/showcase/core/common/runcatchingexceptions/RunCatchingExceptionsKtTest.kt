package io.github.daiji256.showcase.core.common.runcatchingexceptions

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class RunCatchingExceptionsKtTest {
    private class Value

    private val value = Value()
    private val exception = Exception()
    private val cancellationException = CancellationException()
    private val error = Error()
    private val success = Result.success(Value())
    private val failure = Result.failure<Value>(Exception())

    enum class RunTestCase {
        ReturnValue,
        ThrowException,
        ThrowCancellationException,
        ThrowError,
    }

    private fun RunTestCase.block() =
        when (this) {
            RunTestCase.ReturnValue -> value
            RunTestCase.ThrowException -> throw exception
            RunTestCase.ThrowCancellationException -> throw cancellationException
            RunTestCase.ThrowError -> throw error
        }

    private val RunTestCase.expected: Result<Result<Value>>
        get() = when (this) {
            RunTestCase.ReturnValue ->
                Result.success(Result.success(value))

            RunTestCase.ThrowException ->
                Result.success(Result.failure(exception))

            RunTestCase.ThrowCancellationException ->
                Result.success(Result.failure(cancellationException))

            RunTestCase.ThrowError ->
                Result.failure(error)
        }

    private val RunTestCase.suspendExpected: Result<Result<Value>>
        get() = when (this) {
            RunTestCase.ReturnValue ->
                Result.success(Result.success(value))

            RunTestCase.ThrowException ->
                Result.success(Result.failure(exception))

            RunTestCase.ThrowCancellationException ->
                Result.failure(cancellationException)

            RunTestCase.ThrowError ->
                Result.failure(error)
        }

    @Test
    fun runCatchingExceptions(@TestParameter case: RunTestCase) {
        assertEquals(
            runCatching { runCatchingExceptions { case.block() } },
            case.expected,
        )
    }

    @Test
    fun runCatchingExceptions_extension(@TestParameter case: RunTestCase) {
        assertEquals(
            runCatching { Unit.runCatchingExceptions { case.block() } },
            case.expected,
        )
    }

    @Test
    fun suspendRunCatchingExceptions(@TestParameter case: RunTestCase) = runTest {
        assertEquals(
            runCatching { suspendRunCatchingExceptions { case.block() } },
            case.suspendExpected,
        )
    }

    @Test
    fun suspendRunCatchingExceptions_extension(@TestParameter case: RunTestCase) = runTest {
        assertEquals(
            runCatching { Unit.suspendRunCatchingExceptions { case.block() } },
            case.suspendExpected,
        )
    }

    enum class MapTestCase {
        SuccessReturnValue,
        SuccessThrowException,
        SuccessThrowCancellationException,
        SuccessThrowError,
        FailureReturnValue,
        FailureThrowException,
        FailureThrowCancellationException,
        FailureThrowError,
    }

    private val MapTestCase.thiz: Result<Value>
        get() = when (this) {
            MapTestCase.SuccessReturnValue,
            MapTestCase.SuccessThrowException,
            MapTestCase.SuccessThrowCancellationException,
            MapTestCase.SuccessThrowError,
                ->
                success

            MapTestCase.FailureReturnValue,
            MapTestCase.FailureThrowException,
            MapTestCase.FailureThrowCancellationException,
            MapTestCase.FailureThrowError,
                ->
                failure
        }

    private fun MapTestCase.block() =
        when (this) {
            MapTestCase.SuccessReturnValue,
            MapTestCase.FailureReturnValue,
                ->
                value

            MapTestCase.SuccessThrowException,
            MapTestCase.FailureThrowException,
                ->
                throw exception

            MapTestCase.SuccessThrowCancellationException,
            MapTestCase.FailureThrowCancellationException,
                ->
                throw cancellationException

            MapTestCase.SuccessThrowError,
            MapTestCase.FailureThrowError,
                ->
                throw error
        }

    private val MapTestCase.expected: Result<Result<Value>>
        get() = when (this) {
            MapTestCase.SuccessReturnValue ->
                Result.success(Result.success(value))

            MapTestCase.SuccessThrowException ->
                Result.success(Result.failure(exception))

            MapTestCase.SuccessThrowCancellationException ->
                Result.success(Result.failure(cancellationException))

            MapTestCase.SuccessThrowError ->
                Result.failure(error)

            MapTestCase.FailureReturnValue,
            MapTestCase.FailureThrowException,
            MapTestCase.FailureThrowCancellationException,
            MapTestCase.FailureThrowError,
                ->
                Result.success(failure)
        }

    private val MapTestCase.suspendExpected: Result<Result<Value>>
        get() = when (this) {
            MapTestCase.SuccessReturnValue ->
                Result.success(Result.success(value))

            MapTestCase.SuccessThrowException ->
                Result.success(Result.failure(exception))

            MapTestCase.SuccessThrowCancellationException ->
                Result.failure(cancellationException)

            MapTestCase.SuccessThrowError ->
                Result.failure(error)

            MapTestCase.FailureReturnValue,
            MapTestCase.FailureThrowException,
            MapTestCase.FailureThrowCancellationException,
            MapTestCase.FailureThrowError,
                ->
                Result.success(failure)
        }

    @Test
    fun mapCatchingExceptions(@TestParameter case: MapTestCase) {
        assertEquals(
            runCatching {
                case.thiz.mapCatchingExceptions {
                    assertEquals(it, case.thiz.getOrNull())
                    case.block()
                }
            },
            case.expected,
        )
    }

    @Test
    fun suspendMapCatchingExceptions(@TestParameter case: MapTestCase) = runTest {
        assertEquals(
            runCatching {
                case.thiz.suspendMapCatchingExceptions {
                    assertEquals(it, case.thiz.getOrNull())
                    case.block()
                }
            },
            case.suspendExpected,
        )
    }

    enum class RecoverTestCase {
        SuccessReturnValue,
        SuccessThrowException,
        SuccessThrowCancellationException,
        SuccessThrowError,
        FailureReturnValue,
        FailureThrowException,
        FailureThrowCancellationException,
        FailureThrowError,
    }

    private val RecoverTestCase.thiz: Result<Value>
        get() = when (this) {
            RecoverTestCase.SuccessReturnValue,
            RecoverTestCase.SuccessThrowException,
            RecoverTestCase.SuccessThrowCancellationException,
            RecoverTestCase.SuccessThrowError,
                ->
                success

            RecoverTestCase.FailureReturnValue,
            RecoverTestCase.FailureThrowException,
            RecoverTestCase.FailureThrowCancellationException,
            RecoverTestCase.FailureThrowError,
                ->
                failure
        }

    private fun RecoverTestCase.block() =
        when (this) {
            RecoverTestCase.SuccessReturnValue,
            RecoverTestCase.FailureReturnValue,
                ->
                value

            RecoverTestCase.SuccessThrowException,
            RecoverTestCase.FailureThrowException,
                ->
                throw exception

            RecoverTestCase.SuccessThrowCancellationException,
            RecoverTestCase.FailureThrowCancellationException,
                ->
                throw cancellationException

            RecoverTestCase.SuccessThrowError,
            RecoverTestCase.FailureThrowError,
                ->
                throw error
        }

    private val RecoverTestCase.expected: Result<Result<Value>>
        get() = when (this) {
            RecoverTestCase.SuccessReturnValue,
            RecoverTestCase.SuccessThrowException,
            RecoverTestCase.SuccessThrowCancellationException,
            RecoverTestCase.SuccessThrowError,
                ->
                Result.success(success)

            RecoverTestCase.FailureReturnValue ->
                Result.success(Result.success(value))

            RecoverTestCase.FailureThrowException ->
                Result.success(Result.failure(exception))

            RecoverTestCase.FailureThrowCancellationException ->
                Result.success(Result.failure(cancellationException))

            RecoverTestCase.FailureThrowError ->
                Result.failure(error)
        }

    private val RecoverTestCase.suspendExpected: Result<Result<Value>>
        get() = when (this) {
            RecoverTestCase.SuccessReturnValue,
            RecoverTestCase.SuccessThrowException,
            RecoverTestCase.SuccessThrowCancellationException,
            RecoverTestCase.SuccessThrowError,
                ->
                Result.success(success)

            RecoverTestCase.FailureReturnValue ->
                Result.success(Result.success(value))

            RecoverTestCase.FailureThrowException ->
                Result.success(Result.failure(exception))

            RecoverTestCase.FailureThrowCancellationException ->
                Result.failure(cancellationException)

            RecoverTestCase.FailureThrowError ->
                Result.failure(error)
        }

    @Test
    fun recoverCatchingExceptions(@TestParameter case: RecoverTestCase) {
        assertEquals(
            runCatching {
                case.thiz.recoverCatchingExceptions {
                    assertEquals(it, case.thiz.exceptionOrNull())
                    case.block()
                }
            },
            case.expected,
        )
    }

    @Test
    fun suspendRecoverCatchingExceptions(@TestParameter case: RecoverTestCase) = runTest {
        assertEquals(
            runCatching {
                case.thiz.suspendRecoverCatchingExceptions {
                    assertEquals(it, case.thiz.exceptionOrNull())
                    case.block()
                }
            },
            case.suspendExpected,
        )
    }
}
