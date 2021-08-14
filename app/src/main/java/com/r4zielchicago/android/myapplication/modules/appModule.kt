package com.r4zielchicago.android.myapplication.modules

import com.r4zielchicago.android.myapplication.api.HeroesApi
import com.r4zielchicago.android.myapplication.network.NetworkService
import org.koin.dsl.module

val appModule = module {

    val networkService = NetworkService()
    val retrofit = networkService.getRetrofitInstance()
    val heroesApi = retrofit.create(HeroesApi::class.java)

    fun provideHeroesApi() = heroesApi

    single { provideHeroesApi() }
}