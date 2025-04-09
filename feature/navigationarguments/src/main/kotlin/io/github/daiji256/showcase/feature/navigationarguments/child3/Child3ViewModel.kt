package io.github.daiji256.showcase.feature.navigationarguments.child3

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Child3ViewModel @Inject constructor(
    arguments: Child3Arguments,
) : ViewModel() {
    val argument: String = arguments.argument
}
