package io.github.daiji256.showcase.core.common.runcatchingexceptions

import kotlin.coroutines.cancellation.CancellationException

/**
 * Calls the specified function [block] and returns its encapsulated result if invocation was
 * successful, catching any [Exception] that was thrown from the [block] function execution and
 * encapsulating it as a failure.
 *
 * Note: Unlike [runCatching], which catches all [Throwable] (including [Error]), this function only
 * catches [Exception].
 */
inline fun <R> runCatchingExceptions(block: () -> R): Result<R> =
    try {
        Result.success(block())
    } catch (exception: Exception) {
        Result.failure(exception)
    }

/**
 * Calls the specified function [block] with `this` value as its receiver and returns its
 * encapsulated result if invocation was successful, catching any [Exception] that was thrown from
 * the [block] function execution and encapsulating it as a failure.
 *
 * Note: Unlike [runCatching], which catches all [Throwable] (including [Error]), this function only
 * catches [Exception].
 */
inline fun <T, R> T.runCatchingExceptions(block: T.() -> R): Result<R> =
    try {
        Result.success(block())
    } catch (exception: Exception) {
        Result.failure(exception)
    }

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated
 * value if this instance represents [success][Result.isSuccess] or the original encapsulated
 * [Throwable] exception if it is [failure][Result.isFailure].
 *
 * This function catches any [Exception] thrown by [transform] function and encapsulates it as a
 * failure.
 *
 * Note: Unlike [mapCatching], which catches all [Throwable] (including [Error]), this function only
 * catches [Exception].
 */
inline fun <R, T> Result<T>.mapCatchingExceptions(transform: (value: T) -> R): Result<R> =
    when (val exception = exceptionOrNull()) {
        null -> runCatchingExceptions { transform(getOrThrow()) }
        else -> Result.failure(exception)
    }

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated
 * [Throwable] exception if this instance represents [failure][Result.isFailure] or the original
 * encapsulated value if it is [success][Result.isSuccess].
 *
 * This function catches any [Exception] thrown by [transform] function and encapsulates it as a
 * failure.
 *
 * Note: Unlike [recoverCatching], which catches all [Throwable] (including [Error]), this function
 * only catches [Exception].
 */
inline fun <R, T : R> Result<T>.recoverCatchingExceptions(
    transform: (exception: Throwable) -> R,
): Result<R> =
    when (val exception = exceptionOrNull()) {
        null -> this
        else -> runCatchingExceptions { transform(exception) }
    }

/**
 * Calls the specified function [block] and returns its encapsulated result if invocation was
 * successful, catching any [Exception] (except [CancellationException]) that was thrown from the
 * [block] function execution and encapsulating it as a failure.
 *
 * Note: Unlike [runCatching], which catches all [Throwable] (including [Error] and
 * [CancellationException]), this function only catches [Exception] (except
 * [CancellationException]).
 */
suspend inline fun <R> suspendRunCatchingExceptions(block: suspend () -> R): Result<R> =
    try {
        Result.success(block())
    } catch (cancellationException: CancellationException) {
        throw cancellationException
    } catch (exception: Exception) {
        Result.failure(exception)
    }

/**
 * Calls the specified function [block] with `this` value as its receiver and returns its
 * encapsulated result if invocation was successful, catching any [Exception] (except
 * [CancellationException]) that was thrown from the [block] function execution and encapsulating it
 * as a failure.
 *
 * Note: Unlike [runCatching], which catches all [Throwable] (including [Error] and
 * [CancellationException]), this function only catches [Exception] (except
 * [CancellationException]).
 */
suspend inline fun <T, R> T.suspendRunCatchingExceptions(block: suspend T.() -> R): Result<R> =
    try {
        Result.success(block())
    } catch (cancellationException: CancellationException) {
        throw cancellationException
    } catch (exception: Exception) {
        Result.failure(exception)
    }

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated
 * value if this instance represents [success][Result.isSuccess] or the original encapsulated
 * [Throwable] exception if it is [failure][Result.isFailure].
 *
 * This function catches any [Exception] (except [CancellationException]) thrown by [transform]
 * function and encapsulates it as a failure.
 *
 * Note: Unlike [mapCatching], which catches all [Throwable] (including [Error] and
 * [CancellationException]), this function only catches [Exception] (except
 * [CancellationException]).
 */
suspend inline fun <R, T> Result<T>.suspendMapCatchingExceptions(
    transform: suspend (value: T) -> R,
): Result<R> =
    when (val exception = exceptionOrNull()) {
        null -> suspendRunCatchingExceptions { transform(getOrThrow()) }
        else -> Result.failure(exception)
    }

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated
 * [Throwable] exception if this instance represents [failure][Result.isFailure] or the original
 * encapsulated value if it is [success][Result.isSuccess].
 *
 * This function catches any [Exception] (except [CancellationException]) thrown by [transform]
 * function and encapsulates it as a failure.
 *
 * Note: Unlike [recoverCatching], which catches all [Throwable] [Throwable] (including [Error] and
 * [CancellationException]), this function only catches [Exception] (except
 * [CancellationException]).
 */
suspend inline fun <R, T : R> Result<T>.suspendRecoverCatchingExceptions(
    transform: suspend (exception: Throwable) -> R,
): Result<R> =
    when (val exception = exceptionOrNull()) {
        null -> this
        else -> suspendRunCatchingExceptions { transform(exception) }
    }
