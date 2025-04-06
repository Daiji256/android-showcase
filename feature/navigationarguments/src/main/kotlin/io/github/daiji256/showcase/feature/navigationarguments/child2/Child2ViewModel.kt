package io.github.daiji256.showcase.feature.navigationarguments.child2

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Child2ViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val value: String = savedStateHandle.toRoute<Child2ScreenRoute>().value
}
