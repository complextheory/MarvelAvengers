package com.r4zielchicago.android.myapplication.modules

import com.r4zielchicago.android.myapplication.api.ComicsApi
import com.r4zielchicago.android.myapplication.api.MarvelApi
import com.r4zielchicago.android.myapplication.network.NetworkService
import com.r4zielchicago.android.myapplication.utilities.NavControllerUtil
import org.koin.dsl.module
import kotlin.math.sin

val appModule = module {

    val networkService = NetworkService()
    val retrofit = networkService.getRetrofitInstance()
    val marvelApi = retrofit.create(MarvelApi::class.java)
    val comicsApi = retrofit.create(ComicsApi::class.java)
    val navControllerUtil = NavControllerUtil()

    fun provideMarvelApi() = marvelApi
    fun provideComicsApi() = comicsApi
    fun provideNavControllerUtil() = navControllerUtil

    single { provideMarvelApi() }
    single { provideComicsApi() }
    single { provideNavControllerUtil() }
}