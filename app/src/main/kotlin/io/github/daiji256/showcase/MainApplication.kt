package io.github.daiji256.showcase

import android.app.Application

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initNapier()
    }
}
