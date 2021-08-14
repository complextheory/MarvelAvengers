package com.r4zielchicago.android.myapplication.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.r4zielchicago.android.myapplication.api.HeroesApi
import com.r4zielchicago.android.myapplication.repository.HeroRepository
import com.r4zielchicago.android.myapplication.ui.HeroViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val heroesApi: HeroesApi): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroViewModel::class.java)) {
            return HeroViewModel(HeroRepository(heroesApi)) as T
        }
        throw IllegalArgumentException("Unknown Class Name")
    }
}