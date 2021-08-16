package com.r4zielchicago.android.myapplication.ui.details.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4zielchicago.android.myapplication.api.entity.comics.Comic
import com.r4zielchicago.android.myapplication.api.entity.comics.ComicResult
import com.r4zielchicago.android.myapplication.api.entity.events.EventResult
import com.r4zielchicago.android.myapplication.api.entity.events.MarvelEvent
import com.r4zielchicago.android.myapplication.api.entity.local.LocalDetails
import com.r4zielchicago.android.myapplication.api.entity.series.Series
import com.r4zielchicago.android.myapplication.api.entity.series.SeriesResult
import com.r4zielchicago.android.myapplication.repository.DetailsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsViewModel(
    private val detailsRepository: DetailsRepository
    ) : ViewModel() {

    val comicLiveData = MutableLiveData<List<Comic>>()
    val seriesLiveData = MutableLiveData<List<Series>>()
    val eventsLiveData = MutableLiveData<List<MarvelEvent>>()

    fun observeLiveData() {

        fetchData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val comics = it.comicResult.comicsData?.results ?: emptyList()
                    val series = it.seriesResult.seriesData?.results ?: emptyList()
                    val events = it.eventResult.eventsData?.results ?: emptyList()
                    comicLiveData.value = comics
                    seriesLiveData.value = series
                    eventsLiveData.value = events
                },
                {
                    comicLiveData.value = emptyList()
                    RxJavaPlugins.onError(it)
                }
            )
    }

    fun fetchData(): Observable<LocalDetails> {
        return Observable.zip(
            detailsRepository.getComics(),
            detailsRepository.getSeries(),
            detailsRepository.getEvents(),
            {c, s, e -> convert(c, s, e)})
    }

    private fun convert(comics: ComicResult, series: SeriesResult, events: EventResult): LocalDetails? {
        return LocalDetails(comics, series, events)
    }
}