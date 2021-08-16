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
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class DetailsViewModel(
    private val detailsRepository: DetailsRepository,
    private val comicsViewModel: ComicsViewModel,
    private val seriesViewModel: SeriesViewModel,
    private val eventsViewModel: EventsViewModel,
    ) : ViewModel() {

    val comicLiveData = MutableLiveData<List<Comic>>()
    val seriesLiveData = MutableLiveData<List<Series>>()
    val eventsLiveData = MutableLiveData<List<MarvelEvent>>()

    fun fetchDetails() {
        fetchComics()
        fetchSeries()
        fetchEvents()
    }
    fun observeLiveData() {

//        val comics = fetchComics()
//        val series = fetchSeries()
//        val events = fetchEvents()
//
//
//        val zip = object :
//            kotlin.Function3<, List<Series>, List<MarvelEvent>, Observable<LocalDetails>>
//
//
//
//
//        val zipper = object :
//            kotlin.Function3<Observable<ComicResult>, Observable<SeriesResult>, Observable<EventResult>, Any> {
//            override fun invoke(
//                p1: Observable<ComicResult>,
//                p2: Observable<SeriesResult>,
//                p3: Observable<EventResult>
//            ): Any {
//                val data = p1.
//            }
//        }
//
//        return Observable.zip(
//            Observable.just(comics),
//            Observable.just(series),
//            Observable.just(events),
//            zipper
//        )


//
//        val zipper = object :
//            kotlin.Function3<List<Comic>, List<Series>, List<MarvelEvent>, Observable<LocalDetails>> {
//            override fun invoke(
//                p1: List<Comic>,
//                p2: List<Comic>,
//                p3: List<Comic>
//            ): Observable<LocalDetails> {
//
////               return Observable.merge(p1, p2, p3)
//            }
//        }
//
//        Observable.zip(
//            comics,
//            series,
//            events,
//            zipper
//        )

//        Observable.zip(
//            Observable.just(fetchComics()),
//            Observable.just(fetchSeries()),
//            Observable.just(fetchEvents()),
//        )


//        {Triple(fetchComics(), fetchSeries(), fetchEvents())}

//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    val comics = it.comicsData?.results ?: emptyList()
//                    comicLiveData.value = comics
//
//                },
//                {
//                    comicLiveData.value = emptyList()
//                    RxJavaPlugins.onError(it)
//                }
//            )


    }

    fun fetchData(): Observable<LocalDetails> {
        return Observable.zip(fetchComics(), fetchSeries(), fetchEvents(), {c, s, e -> convert(c, s, e)})
    }

    private fun convert(comics: ComicResult, series: SeriesResult, events: EventResult): LocalDetails? {

       return object :
        Function3<ComicResult, SeriesResult, EventResult, LocalDetails> {
           override fun invoke(p1: ComicResult, p2: SeriesResult, p3: EventResult): LocalDetails {
               TODO("Not yet implemented")
           }

       }

//        return Observable.fromArray(comics, series, events)
    }

    fun fetchComics(): Observable<ComicResult> {
        detailsRepository.getComics()
    }

    fun fetchSeries(): Observable<SeriesResult> {
        return detailsRepository.getSeries()
    }

    fun fetchEvents(): Observable<EventResult> {
        return detailsRepository.getEvents()
    }




}