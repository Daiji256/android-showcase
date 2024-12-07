package io.github.daiji256.feature.ktlint

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun KtlintScreen(
    modifier: Modifier = Modifier,
) {
    // TODO
    Scaffold(
        modifier = modifier,
    ) { padding ->
        Text(
            text = ":feature:ktlint",
            modifier = Modifier.padding(padding),
        )
    }
}

@Preview
@Composable
private fun KtlintScreenPreview() {
    // TODO: Theme
    KtlintScreen()
}
