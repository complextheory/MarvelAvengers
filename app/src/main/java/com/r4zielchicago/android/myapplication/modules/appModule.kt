package com.r4zielchicago.android.myapplication.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.r4zielchicago.android.myapplication.api.MarvelApi
import com.r4zielchicago.android.myapplication.network.NetworkService
import com.r4zielchicago.android.myapplication.utilities.NavControllerUtil
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    val networkService = NetworkService()
    val retrofit = networkService.getRetrofitInstance()
    val marvelApi = retrofit.create(MarvelApi::class.java)
    val navControllerUtil = NavControllerUtil()
    val gson = Gson()

    fun provideGson() = gson
    fun provideMarvelApi() = marvelApi
    fun provideNavControllerUtil() = navControllerUtil
    fun provideSharedPrefs(app: Application): SharedPreferences {
        return app.applicationContext.getSharedPreferences(
            "MARVEL_SHARED_PREFS",
            Context.MODE_PRIVATE
        )
    }

    single { provideGson() }
    single { provideMarvelApi() }
    single { provideNavControllerUtil() }
    single { provideSharedPrefs(androidApplication())}
}