package io.github.daiji256.showcase.feature.mixedfonts

import android.content.Context
import androidx.compose.ui.text.font.AndroidFont
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontLoadingStrategy
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import android.graphics.Typeface as NativeTypeface
import android.graphics.fonts.Font as NativeFont
import android.graphics.fonts.FontFamily as NativeFontFamily

val MyFontFamily = FontFamily(
    fonts = listOf(
        MyFont(weight = FontWeight.Normal),
        MyFont(weight = FontWeight.Bold),
    ),
)

private class MyFont(
    override val weight: FontWeight,
) : AndroidFont(
    loadingStrategy = FontLoadingStrategy.Blocking,
    typefaceLoader = MyFontTypefaceLoader,
    variationSettings = FontVariation.Settings(),
) {
    override val style: FontStyle = FontStyle.Normal

    private var typeface: NativeTypeface? = null

    private fun load(context: Context): NativeTypeface {
        val quicksandFont = NativeFont.Builder(
            context.resources,
            R.font.quicksand,
        )
            .setWeight(weight.weight)
            .setFontVariationSettings("'wght' ${weight.weight}")
            .build()
        val quicksandFontFamily = NativeFontFamily.Builder(quicksandFont).build()

        val zenMaruGothicFont = NativeFont.Builder(
            context.resources,
            when {
                weight <= FontWeight.Normal -> R.font.zen_maru_gothic_regular
                else -> R.font.zen_maru_gothic_bold
            },
        )
            .setWeight(weight.weight)
            .build()
        val zenMaruGothicFontFamily = NativeFontFamily.Builder(zenMaruGothicFont).build()

        return NativeTypeface.CustomFallbackBuilder(quicksandFontFamily)
            .addCustomFallback(zenMaruGothicFontFamily)
            .setSystemFallback("sans-serif")
            .build()
            .also { typeface = it }
    }

    private object MyFontTypefaceLoader : TypefaceLoader {
        override fun loadBlocking(context: Context, font: AndroidFont): NativeTypeface? =
            (font as? MyFont)?.run { typeface ?: load(context) }

        override suspend fun awaitLoad(context: Context, font: AndroidFont): Nothing =
            throw UnsupportedOperationException("MyFont is blocking")
    }
}
