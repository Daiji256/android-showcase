package io.github.daiji256.showcase.feature.localsnackbarhoststate

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.feature.localsnackbarhoststate.first.localSnackbarHostStateFirst
import io.github.daiji256.showcase.feature.localsnackbarhoststate.second.localSnackbarHostStateSecond

fun EntryProviderScope<NavKey>.localSnackbarHostState() {
    localSnackbarHostStateFirst()
    localSnackbarHostStateSecond()
}
