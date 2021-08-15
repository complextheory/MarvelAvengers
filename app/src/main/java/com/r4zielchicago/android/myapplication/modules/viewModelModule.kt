package com.r4zielchicago.android.myapplication.modules

import com.r4zielchicago.android.myapplication.ui.details.DetailsViewModel
import com.r4zielchicago.android.myapplication.ui.hero.HeroViewModel
import org.koin.dsl.module
import kotlin.math.sin

val viewModelModule = module {
    single { HeroViewModel(get()) }
    single { DetailsViewModel(get()) }
}
