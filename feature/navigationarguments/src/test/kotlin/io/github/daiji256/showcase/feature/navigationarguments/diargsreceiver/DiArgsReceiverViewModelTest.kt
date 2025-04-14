package io.github.daiji256.showcase.feature.navigationarguments.diargsreceiver

import kotlin.test.assertEquals
import org.junit.Test

class DiArgsReceiverViewModelTest {
    @Test
    fun test_dummy_arg() {
        val viewModel = DiArgsReceiverViewModel(
            args = object : DiArgsReceiverArgs {
                override val arg: String = "dummy"
            },
        )
        assertEquals(viewModel.arg, "dummy")
    }
}
