package io.github.daiji256.showcase.feature.navigationarguments.diargsreceiver

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DiArgsReceiverViewModel @Inject constructor(
    args: DiArgsReceiverArgs,
) : ViewModel() {
    val arg: String = args.arg
}
