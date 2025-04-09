package io.github.daiji256.showcase.feature.navigationarguments.child3

import kotlin.test.assertEquals
import org.junit.Test

class Child3ViewModelTest {
    @Test
    fun test_dummy_argument() {
        val viewModel = Child3ViewModel(
            arguments = object : Child3Arguments {
                override val argument: String = "dummy"
            },
        )
        assertEquals(viewModel.argument, "dummy")
    }
}
