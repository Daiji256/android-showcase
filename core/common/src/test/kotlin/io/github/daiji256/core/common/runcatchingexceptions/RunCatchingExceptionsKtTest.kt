package io.github.daiji256.core.common.runcatchingexceptions

import com.google.common.truth.Truth.assertThat
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import java.util.concurrent.CancellationException
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class RunCatchingExceptionsKtTest {
    class Value

    val value = Value()
    val exception = Exception()
    val cancellationException = CancellationException()
    val error = Error()
    val success = Result.success(Value())
    val failure = Result.failure<Value>(Exception())

    enum class RunTestCase {
        ReturnValue,
        ThrowException,
        ThrowCancellationException,
        ThrowError,
    }

    fun RunTestCase.block() =
        when (this) {
            RunTestCase.ReturnValue -> value
            RunTestCase.ThrowException -> throw exception
            RunTestCase.ThrowCancellationException -> throw cancellationException
            RunTestCase.ThrowError -> throw error
        }

    val RunTestCase.expected: Result<Result<Value>>
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

    val RunTestCase.suspendExpected: Result<Result<Value>>
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
        assertThat(
            runCatching { runCatchingExceptions { case.block() } },
        ).isEqualTo(
            case.expected,
        )
    }

    @Test
    fun runCatchingExceptions_extension(@TestParameter case: RunTestCase) {
        assertThat(
            runCatching { Unit.runCatchingExceptions { case.block() } },
        ).isEqualTo(
            case.expected,
        )
    }

    @Test
    fun suspendRunCatchingExceptions(@TestParameter case: RunTestCase) = runTest {
        assertThat(
            runCatching { suspendRunCatchingExceptions { case.block() } },
        ).isEqualTo(
            case.suspendExpected,
        )
    }

    @Test
    fun suspendRunCatchingExceptions_extension(@TestParameter case: RunTestCase) = runTest {
        assertThat(
            runCatching { Unit.suspendRunCatchingExceptions { case.block() } },
        ).isEqualTo(
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

    val MapTestCase.thiz: Result<Value>
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

    fun MapTestCase.block() =
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

    val MapTestCase.expected: Result<Result<Value>>
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

    val MapTestCase.suspendExpected: Result<Result<Value>>
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
        assertThat(
            runCatching {
                case.thiz.mapCatchingExceptions {
                    assertThat(it).isEqualTo(case.thiz.getOrNull())
                    case.block()
                }
            },
        ).isEqualTo(
            case.expected,
        )
    }

    @Test
    fun suspendMapCatchingExceptions(@TestParameter case: MapTestCase) = runTest {
        assertThat(
            runCatching {
                case.thiz.suspendMapCatchingExceptions {
                    assertThat(it).isEqualTo(case.thiz.getOrNull())
                    case.block()
                }
            },
        ).isEqualTo(
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

    val RecoverTestCase.thiz: Result<Value>
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

    fun RecoverTestCase.block() =
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

    val RecoverTestCase.expected: Result<Result<Value>>
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

    val RecoverTestCase.suspendExpected: Result<Result<Value>>
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
        assertThat(
            runCatching {
                case.thiz.recoverCatchingExceptions {
                    assertThat(it).isEqualTo(case.thiz.exceptionOrNull())
                    case.block()
                }
            },
        ).isEqualTo(
            case.expected,
        )
    }

    @Test
    fun suspendRecoverCatchingExceptions(@TestParameter case: RecoverTestCase) = runTest {
        assertThat(
            runCatching {
                case.thiz.suspendRecoverCatchingExceptions {
                    assertThat(it).isEqualTo(case.thiz.exceptionOrNull())
                    case.block()
                }
            },
        ).isEqualTo(
            case.suspendExpected,
        )
    }
}
