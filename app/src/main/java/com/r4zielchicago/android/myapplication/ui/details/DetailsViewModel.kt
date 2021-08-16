package com.r4zielchicago.android.myapplication.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4zielchicago.android.myapplication.api.entity.comics.Comic
import com.r4zielchicago.android.myapplication.api.entity.events.MarvelEvent
import com.r4zielchicago.android.myapplication.api.entity.series.Series
import com.r4zielchicago.android.myapplication.repository.DetailsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins.onError
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsViewModel(private val detailsRepository: DetailsRepository) : ViewModel() {

    val comicLiveData = MutableLiveData<List<Comic>>()
    val seriesLiveData = MutableLiveData<List<Series>>()
    val eventsLiveData = MutableLiveData<List<MarvelEvent>>()

    fun fetchComics() {
        detailsRepository.getComics()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val comics = it.comicsData?.results ?: emptyList()
                    comicLiveData.value = comics

                },
                {
                    comicLiveData.value = emptyList()
                    onError(it)
                }
            )
    }

    fun fetchSeries() {
        detailsRepository.getSeries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val series = it.seriesData?.results ?: emptyList()
                    seriesLiveData.value = series

                },
                {
                    seriesLiveData.value = emptyList()
                    onError(it)
                }
            )
    }

    fun fetchEvents() {
        detailsRepository.getEvents()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val events = it.eventsData?.results ?: emptyList()
                    eventsLiveData.value = events
                },
                {
                    eventsLiveData.value = emptyList()
                    onError(it)
                }
            )
    }

}