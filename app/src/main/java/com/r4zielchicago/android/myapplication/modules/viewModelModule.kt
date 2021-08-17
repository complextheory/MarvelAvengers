package com.r4zielchicago.android.myapplication.modules

import com.r4zielchicago.android.myapplication.ui.details.viewModel.DetailsViewModel
import com.r4zielchicago.android.myapplication.ui.hero.HeroViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HeroViewModel(get(), get()) }
    single { DetailsViewModel(get(), get(), get()) }
}
