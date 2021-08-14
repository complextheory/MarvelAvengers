package com.r4zielchicago.android.myapplication.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4zielchicago.android.myapplication.api.entity.Data
import com.r4zielchicago.android.myapplication.api.entity.Hero
import com.r4zielchicago.android.myapplication.repository.HeroRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins.onError
import io.reactivex.rxjava3.schedulers.Schedulers

class HeroViewModel(private val heroRepository: HeroRepository) : ViewModel() {

    val heroLiveData = MutableLiveData<List<Hero>>()
    val tempHeroListLiveData = MutableLiveData<List<Hero>>()
    private val tempHeroList = mutableListOf<Hero>()
    private var isListSortedAscending: Boolean = true

    fun fetchHeroes() {
        heroRepository.getHeroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    heroLiveData.value = it?.data?.heroes
                    tempHeroList.clear()
                    heroLiveData.value?.forEach {  hero ->
                        tempHeroList.add(hero)
                    }
                },
                {
                    heroLiveData.value = emptyList()
                    onError(it)
                }
            )
    }

    fun handleClick(view: View) {
        Log.wtf("Coming From HandleClick", "Handling Click")

        isListSortedAscending = if (isListSortedAscending) {
            tempHeroList.sortByDescending { it.name }
            false
        }else{
            tempHeroList.sortBy { it.name }
            true
        }

//        tempHeroListLiveData.value = tempHeroList
        tempHeroListLiveData.postValue(tempHeroList)
    }
}