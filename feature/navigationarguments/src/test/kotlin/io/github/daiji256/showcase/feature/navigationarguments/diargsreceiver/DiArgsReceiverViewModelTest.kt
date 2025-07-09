package io.github.daiji256.showcase.feature.navigationarguments.diargsreceiver

import kotlin.test.Test
import kotlin.test.assertEquals

class DiArgsReceiverViewModelTest {
    @Test
    fun test_dummy_arg() {
        val viewModel = DiArgsReceiverViewModel(
            route = DiArgsReceiverScreenRoute(arg = "dummy"),
        )
        assertEquals(viewModel.arg, "dummy")
    }
}
