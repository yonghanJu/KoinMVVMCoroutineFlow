package com.hci.koinmvvmcoroutineflow.config

import android.app.Application
import com.hci.koinmvvmcoroutineflow.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class AppMain: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AppMain)
            modules(AppModules.modules)
        }
    }
}