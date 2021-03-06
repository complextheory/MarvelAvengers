package com.r4zielchicago.android.myapplication.repository

import com.r4zielchicago.android.myapplication.Constants
import com.r4zielchicago.android.myapplication.api.MarvelApi
import com.r4zielchicago.android.myapplication.api.entity.comics.ComicResult
import com.r4zielchicago.android.myapplication.api.entity.events.EventResult
import com.r4zielchicago.android.myapplication.api.entity.series.SeriesResult
import io.reactivex.rxjava3.core.Observable

class DetailsRepository(private val marvelApi: MarvelApi) {

    fun getComics(): Observable<ComicResult> = marvelApi.getComics(
            ts = Constants.ts,
            apikey = Constants.API_KEY,
            hash = Constants.hash(),
            limit = Constants.limit
    )

    fun getSeries(): Observable<SeriesResult> = marvelApi.getSeries(
        ts = Constants.ts,
        apikey = Constants.API_KEY,
        hash = Constants.hash(),
        limit = Constants.limit
    )

    fun getEvents(): Observable<EventResult> = marvelApi.getEvents(
        ts = Constants.ts,
        apikey = Constants.API_KEY,
        hash = Constants.hash(),
        limit = Constants.limit
    )
}