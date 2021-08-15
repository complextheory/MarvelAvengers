package com.r4zielchicago.android.myapplication.modules

import com.r4zielchicago.android.myapplication.api.HeroesApi
import com.r4zielchicago.android.myapplication.network.NetworkService
import com.r4zielchicago.android.myapplication.utilities.NavControllerUtil
import org.koin.dsl.module

val appModule = module {

    val networkService = NetworkService()
    val retrofit = networkService.getRetrofitInstance()
    val heroesApi = retrofit.create(HeroesApi::class.java)
    val navControllerUtil = NavControllerUtil()

    fun provideHeroesApi() = heroesApi
    fun provideNavControllerUtil() = navControllerUtil

    single { provideHeroesApi() }
    single { provideNavControllerUtil() }
}