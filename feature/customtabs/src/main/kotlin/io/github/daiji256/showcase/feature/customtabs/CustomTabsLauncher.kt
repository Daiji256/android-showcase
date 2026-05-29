package io.github.daiji256.showcase.feature.customtabs

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import io.github.aakira.napier.Napier

/**
 * Launcher for Custom Tabs.
 */
class CustomTabsLauncher(private val context: Context) {
    private val activity by lazy { context.findActivity() }

    /**
     * Launches the specified [uri] in a Custom Tab.
     *
     * If no [Activity] can be found in the provided [context],
     * the Custom Tab is started as a new task.
     *
     * Shows a Toast instead of throwing [ActivityNotFoundException]
     * if Custom Tab cannot be launched.
     *
     * Note: [ActivityNotFoundException] is not thrown if Custom Tab cannot be launched.
     */
    fun launch(uri: Uri) {
        try {
            CustomTabsIntent.Builder().build()
                .apply {
                    if (activity == null) {
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                }
                .launchUrl(activity ?: context, uri)
        } catch (e: ActivityNotFoundException) {
            Napier.e(e) { "Can't open $uri." }
            Toast.makeText(activity ?: context, "Can't open $uri.", Toast.LENGTH_SHORT).show()
        }
    }
}

private fun Context.findActivity(): Activity? =
    when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }

/**
 * Creates a [CustomTabsLauncher] that is remembered across compositions.
 */
@Composable
fun rememberCustomTabsLauncher(
    context: Context = LocalContext.current,
): CustomTabsLauncher =
    remember(context) { CustomTabsLauncher(context = context) }
