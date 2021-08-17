package com.r4zielchicago.android.myapplication.ui.hero

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4zielchicago.android.myapplication.api.entity.heroes.Hero
import com.r4zielchicago.android.myapplication.repository.HeroRepository
import com.r4zielchicago.android.myapplication.utilities.SharedPrefsUtil
import com.r4zielchicago.android.myapplication.utilities.heroKey
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins.onError
import io.reactivex.rxjava3.schedulers.Schedulers

class HeroViewModel(private val heroRepository: HeroRepository, private val prefsUtil: SharedPrefsUtil) : ViewModel() {

    val heroLiveData = MutableLiveData<List<Hero>>()
    val tempHeroListLiveData = MutableLiveData<List<Hero>>()
    val heroList = mutableListOf<Hero>()
    private var isListSortedAscending: Boolean = true

    fun fetchHeroes() {
        heroRepository.getHeroes()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val heroes = it?.heroData?.heroes ?: emptyList()
                    heroLiveData.value = heroes
                    tempHeroListLiveData.value = heroes
                    heroList.clear()
                    heroList.addAll(heroes)
                },
                {
                    heroLiveData.value = prefsUtil.getFromPrefs(heroKey)
                    tempHeroListLiveData.value = prefsUtil.getFromPrefs(heroKey)
                    heroList.clear()
                    onError(it)
                }
            )
    }

    fun onSortClicked(view: View) {
        isListSortedAscending = !isListSortedAscending
        if (isListSortedAscending) heroList.sortBy { it.name }
        else heroList.sortByDescending { it.name }

        tempHeroListLiveData.postValue(heroList)
    }
}