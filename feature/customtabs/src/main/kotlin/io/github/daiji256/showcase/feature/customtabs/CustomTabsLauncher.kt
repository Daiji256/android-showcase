package io.github.daiji256.showcase.feature.customtabs

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

interface CustomTabsLauncher {
    fun launch(uri: Uri)
}

private class CustomTabsLauncherImpl(val context: Context) : CustomTabsLauncher {
    override fun launch(uri: Uri) {
        try {
            CustomTabsIntent.Builder().build()
                .apply {
                    if (context !is Activity) {
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                }
                .launchUrl(context, uri)
        } catch (e: ActivityNotFoundException) {
            logger.error(e) { "Can't open $uri." }
            Toast.makeText(context, "Can't open $uri.", Toast.LENGTH_SHORT).show()
        }
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object CustomTabsLauncherModule {
    @Provides
    fun providesCustomTabsLauncher(@ApplicationContext context: Context): CustomTabsLauncher =
        CustomTabsLauncherImpl(context = context)
}

@Composable
fun rememberCustomTabsLauncher(): CustomTabsLauncher {
    val context = LocalActivity.current ?: LocalContext.current
    return remember(context) { CustomTabsLauncherImpl(context = context) }
}
