package com.hci.koinmvvmcoroutineflow.config

import android.app.Application
import com.hci.koinmvvmcoroutineflow.DI.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppMain: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppMain)
            modules(AppModules.modules)
        }
    }
}