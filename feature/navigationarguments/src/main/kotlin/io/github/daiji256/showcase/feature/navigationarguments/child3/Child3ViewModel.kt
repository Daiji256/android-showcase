package io.github.daiji256.showcase.feature.navigationarguments.child3

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Child3ViewModel @Inject constructor(
    argumentsGetter: Child3ArgumentsGetter,
) : ViewModel() {
    val value: String = argumentsGetter.getValue()
}
