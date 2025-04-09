package io.github.daiji256.showcase.feature.navigationarguments.child2

import androidx.lifecycle.SavedStateHandle
import kotlin.test.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Child2ViewModelTest {
    @Test
    fun test_dummy_argument() {
        val savedStateHandle = SavedStateHandle()
        savedStateHandle["argument"] = "dummy"
        val viewModel = Child2ViewModel(
            savedStateHandle = savedStateHandle,
        )
        assertEquals(viewModel.argument, "dummy")
    }
}
