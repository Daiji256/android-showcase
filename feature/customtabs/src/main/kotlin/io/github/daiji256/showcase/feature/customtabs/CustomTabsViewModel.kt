package io.github.daiji256.showcase.feature.customtabs

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CustomTabsViewModel @Inject constructor(
    private val customTabsLauncher: CustomTabsLauncher,
) : ViewModel() {
    fun onOpenUriClick(uri: Uri) = customTabsLauncher.launch(uri = uri)
}
