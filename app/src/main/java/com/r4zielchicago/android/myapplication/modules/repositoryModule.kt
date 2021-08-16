package com.r4zielchicago.android.myapplication.modules

import com.r4zielchicago.android.myapplication.repository.DetailsRepository
import com.r4zielchicago.android.myapplication.repository.HeroRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HeroRepository(get()) }
    single {DetailsRepository(get())}
}
