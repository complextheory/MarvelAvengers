package com.r4zielchicago.android.myapplication.api

import com.r4zielchicago.android.myapplication.api.entity.MarvelResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesApi {

    @GET("characters")
    fun getHeroes(
        @Query("ts") ts: String,
        @Query("apikey")apikey: String,
        @Query("hash")hash: String,
        @Query("limit")limit: String
    ): Observable<MarvelResult>
}