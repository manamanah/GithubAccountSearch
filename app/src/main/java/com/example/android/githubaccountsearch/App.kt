package com.example.android.githubaccountsearch

import android.app.Application
import com.example.android.githubaccountsearch.di.networkModule
import com.example.android.githubaccountsearch.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }

        startKoin {
            // declare used Android context
            androidContext(this@App)
            androidLogger(level = Level.INFO)
            modules(listOf(networkModule, uiModule))
        }
    }
}