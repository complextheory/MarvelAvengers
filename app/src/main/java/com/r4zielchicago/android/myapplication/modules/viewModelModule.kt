package com.r4zielchicago.android.myapplication.modules

import com.r4zielchicago.android.myapplication.ui.details.viewModel.ComicsViewModel
import com.r4zielchicago.android.myapplication.ui.details.viewModel.DetailsViewModel
import com.r4zielchicago.android.myapplication.ui.details.viewModel.EventsViewModel
import com.r4zielchicago.android.myapplication.ui.details.viewModel.SeriesViewModel
import com.r4zielchicago.android.myapplication.ui.hero.HeroViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HeroViewModel(get()) }
    single { DetailsViewModel(get(), get(), get(), get()) }
    single { ComicsViewModel(get())}
    single { SeriesViewModel(get())}
    single { EventsViewModel(get()) }
}
