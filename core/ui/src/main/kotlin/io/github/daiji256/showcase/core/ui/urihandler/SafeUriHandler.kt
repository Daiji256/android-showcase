package io.github.daiji256.showcase.core.ui.urihandler

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.ui.platform.UriHandler
import androidx.core.net.toUri
import io.github.aakira.napier.Napier

/**
 * A [UriHandler] that shows a Toast instead of throwing [ActivityNotFoundException].
 *
 * Note: [ActivityNotFoundException] is not thrown if the URI cannot be opened.
 */
class SafeUriHandler(private val context: Context) : UriHandler {
    /**
     * Open given [uri] in browser.
     *
     * Shows a Toast instead of throwing [ActivityNotFoundException] if [uri] cannot be opened.
     *
     * Note: [ActivityNotFoundException] is not thrown if [uri] cannot be opened.
     */
    override fun openUri(uri: String) {
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, uri.toUri()))
        } catch (e: ActivityNotFoundException) {
            Napier.e(e) { "Can't open $uri." }
            Toast.makeText(context, "Can't open $uri.", Toast.LENGTH_SHORT).show()
        }
    }
}
