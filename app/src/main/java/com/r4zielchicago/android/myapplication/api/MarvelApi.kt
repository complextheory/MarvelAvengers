package com.r4zielchicago.android.myapplication.api

import com.r4zielchicago.android.myapplication.api.entity.comics.ComicResult
import com.r4zielchicago.android.myapplication.api.entity.events.EventResult
import com.r4zielchicago.android.myapplication.api.entity.heroes.HeroResult
import com.r4zielchicago.android.myapplication.api.entity.series.SeriesResult
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getHeroes(
        @Query("ts") ts: String,
        @Query("apikey")apikey: String,
        @Query("hash")hash: String,
        @Query("limit")limit: String
    ): Observable<HeroResult>

    @GET("comics")
    fun getComics(
        @Query("ts") ts: String,
        @Query("apikey")apikey: String,
        @Query("hash")hash: String,
        @Query("limit")limit: String
    ): Observable<ComicResult>

    @GET("series")
    fun getSeries(
        @Query("ts") ts: String,
        @Query("apikey")apikey: String,
        @Query("hash")hash: String,
        @Query("limit")limit: String
    ): Observable<SeriesResult>

    @GET("events")
    fun getEvents(
        @Query("ts") ts: String,
        @Query("apikey")apikey: String,
        @Query("hash")hash: String,
        @Query("limit")limit: String
    ): Observable<EventResult>
}