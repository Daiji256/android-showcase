package io.github.daiji256.showcase.feature.hiltcomposable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

val hiltComposableTitle: String
    @Composable get() = stringResource(id = R.string.feature_hilt_comp_title)
