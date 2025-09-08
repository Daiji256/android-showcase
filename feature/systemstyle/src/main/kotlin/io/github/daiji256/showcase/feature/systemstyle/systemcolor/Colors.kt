package io.github.daiji256.showcase.feature.systemstyle.systemcolor

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun primaryColor(): Color =
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE ->
            when (isSystemInDarkTheme()) {
                false -> colorResource(id = android.R.color.system_primary_light)
                true -> colorResource(id = android.R.color.system_primary_dark)
            }

        else ->
            when (isSystemInDarkTheme()) {
                false -> colorResource(id = android.R.color.system_accent1_400)
                true -> colorResource(id = android.R.color.system_accent1_800)
            }
    }

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun secondaryColor(): Color =
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE ->
            when (isSystemInDarkTheme()) {
                false -> colorResource(id = android.R.color.system_secondary_light)
                true -> colorResource(id = android.R.color.system_secondary_dark)
            }

        else ->
            when (isSystemInDarkTheme()) {
                false -> colorResource(id = android.R.color.system_accent2_400)
                true -> colorResource(id = android.R.color.system_accent2_800)
            }
    }

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun tertiaryColor(): Color =
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE ->
            when (isSystemInDarkTheme()) {
                false -> colorResource(id = android.R.color.system_tertiary_light)
                true -> colorResource(id = android.R.color.system_tertiary_dark)
            }

        else ->
            when (isSystemInDarkTheme()) {
                false -> colorResource(id = android.R.color.system_accent3_400)
                true -> colorResource(id = android.R.color.system_accent3_800)
            }
    }
