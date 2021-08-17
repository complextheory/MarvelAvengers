package com.r4zielchicago.android.myapplication.ui.details.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4zielchicago.android.myapplication.api.entity.comics.Comic
import com.r4zielchicago.android.myapplication.api.entity.events.MarvelEvent
import com.r4zielchicago.android.myapplication.api.entity.heroes.Hero
import com.r4zielchicago.android.myapplication.api.entity.local.LocalDetails
import com.r4zielchicago.android.myapplication.api.entity.series.Series
import com.r4zielchicago.android.myapplication.repository.DetailsRepository
import com.r4zielchicago.android.myapplication.repository.HeroRepository
import com.r4zielchicago.android.myapplication.utilities.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsViewModel(
    private val detailsRepository: DetailsRepository,
    private val heroRepository: HeroRepository,
    private val prefsUtil: SharedPrefsUtil
) : ViewModel() {

    val comicLiveData = MutableLiveData<List<Comic>>()
    val seriesLiveData = MutableLiveData<List<Series>>()
    val eventsLiveData = MutableLiveData<List<MarvelEvent>>()
    val heroesLiveData = MutableLiveData<List<Hero>>()


    fun observeAndFetchData() {

        fetchData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val comics = it.comicResult.comicsData?.results ?: emptyList()
                    val series = it.seriesResult.seriesData?.results ?: emptyList()
                    val events = it.eventResult.eventsData?.results ?: emptyList()
                    val heroes = it.heroResult.heroData.heroes

                    prefsUtil.saveToPrefs(heroes, heroKey)
                    prefsUtil.saveToPrefs(comics, comicsKey)
                    prefsUtil.saveToPrefs(series, seriesKey)
                    prefsUtil.saveToPrefs(events, eventsKey)

                    comicLiveData.value = comics
                    seriesLiveData.value = series
                    eventsLiveData.value = events
                    heroesLiveData.value = heroes
                },
                {
                    comicLiveData.value = emptyList()
                    RxJavaPlugins.onError(it)
                }
            )
    }

    private fun fetchData(): Observable<LocalDetails> {
        return Observable.zip(
            detailsRepository.getComics().subscribeOn(Schedulers.io()),
            detailsRepository.getSeries().subscribeOn(Schedulers.io()),
            detailsRepository.getEvents().subscribeOn(Schedulers.io()),
            heroRepository.getHeroes().subscribeOn(Schedulers.io()),
            {c, s, e, h -> LocalDetails(c, s, e, h)})
    }

}