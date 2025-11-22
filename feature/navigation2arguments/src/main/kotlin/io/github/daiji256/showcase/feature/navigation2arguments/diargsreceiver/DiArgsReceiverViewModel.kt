package io.github.daiji256.showcase.feature.navigation2arguments.diargsreceiver

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DiArgsReceiverViewModel @Inject constructor(
    route: DiArgsReceiverRoute,
) : ViewModel() {
    val arg: String = route.arg
}
