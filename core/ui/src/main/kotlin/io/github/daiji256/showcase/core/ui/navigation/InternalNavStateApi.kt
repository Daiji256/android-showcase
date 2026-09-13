package io.github.daiji256.showcase.core.ui.navigation

@RequiresOptIn(
    message = "Prefer rememberNavState instead. Intended for internal use, previews, or tests.",
    level = RequiresOptIn.Level.ERROR,
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
)
annotation class InternalNavStateApi
