package com.r4zielchicago.android.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4zielchicago.android.myapplication.api.entity.Hero
import com.r4zielchicago.android.myapplication.repository.HeroRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins.onError
import io.reactivex.rxjava3.schedulers.Schedulers

class HeroViewModel(private val heroRepository: HeroRepository) : ViewModel() {

    val heroLiveData = MutableLiveData<List<Hero>>()

    fun fetchHeroes() {
        heroRepository.getHeroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { heroLiveData.value = it?.data?.heroes },
                {
                    heroLiveData.value = emptyList()
                    onError(it)
                }
            )
    }


}