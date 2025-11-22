package io.github.daiji256.showcase.feature.navigation2arguments.viewmodelargsreceiver

import androidx.lifecycle.SavedStateHandle
import kotlin.test.Test
import kotlin.test.assertEquals
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ViewModelArgsReceiverViewModelTest {
    @Test
    fun test_dummy_arg() {
        val savedStateHandle = SavedStateHandle()
        savedStateHandle["arg"] = "dummy"
        val viewModel = ViewModelArgsReceiverViewModel(
            savedStateHandle = savedStateHandle,
        )
        assertEquals(viewModel.arg, "dummy")
    }
}
