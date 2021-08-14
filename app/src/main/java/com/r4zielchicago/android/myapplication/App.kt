package com.r4zielchicago.android.myapplication

import android.app.Application
import com.r4zielchicago.android.myapplication.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repositoryModule, viewModelModule))
        }
    }
}