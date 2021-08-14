package com.r4zielchicago.android.myapplication.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4zielchicago.android.myapplication.api.entity.Hero
import com.r4zielchicago.android.myapplication.repository.HeroRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins.onError
import io.reactivex.rxjava3.schedulers.Schedulers

class HeroViewModel(private val heroRepository: HeroRepository) : ViewModel() {

    val heroLiveData = MutableLiveData<List<Hero>>()
    val tempHeroListLiveData = MutableLiveData<List<Hero>>()
    val heroList = mutableListOf<Hero>()
    private var isListSortedAscending: Boolean = true

    fun fetchHeroes() {
        heroRepository.getHeroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val heroes = it?.data?.heroes ?: emptyList()
                    heroLiveData.value = heroes
                    tempHeroListLiveData.value = heroes
                    heroList.clear()
                    heroList.addAll(heroes)
                },
                {
                    heroLiveData.value = emptyList()
                    tempHeroListLiveData.value = emptyList()
                    heroList.clear()
                    onError(it)
                }
            )
    }

    fun onSortClicked(view: View) {
        Log.i("Coming From HandleClick", "Handling Click")

        isListSortedAscending = !isListSortedAscending
        if (isListSortedAscending) heroList.sortBy { it.name }
        else heroList.sortByDescending { it.name }

        tempHeroListLiveData.postValue(heroList)
    }
}