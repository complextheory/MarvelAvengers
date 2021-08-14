package com.r4zielchicago.android.myapplication.modules

import com.r4zielchicago.android.myapplication.ui.HeroViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HeroViewModel(get()) }
}
