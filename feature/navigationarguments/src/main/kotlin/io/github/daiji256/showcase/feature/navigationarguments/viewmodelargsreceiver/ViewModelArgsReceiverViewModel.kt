package io.github.daiji256.showcase.feature.navigationarguments.viewmodelargsreceiver

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ViewModelArgsReceiverViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val arg: String = savedStateHandle.toRoute<ViewModelArgsReceiverScreenRoute>().arg
}
