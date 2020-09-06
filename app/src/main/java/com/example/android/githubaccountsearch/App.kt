package com.example.android.githubaccountsearch

import android.app.Application
import com.example.android.githubaccountsearch.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // declare used Android context
            androidContext(this@App)
            // in current stable version 2.1.6 bug if using Level below error: https://github.com/InsertKoinIO/koin/issues/847
            // fixed in current unstable version
            androidLogger(level = Level.INFO)

            modules(networkModule)
        }
    }
}