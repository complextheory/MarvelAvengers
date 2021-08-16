package com.r4zielchicago.android.myapplication.api

import com.r4zielchicago.android.myapplication.api.entity.comics.ComicResult
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsApi {
    @GET("comics")
    fun getComics(
        @Query("ts") ts: String,
        @Query("apikey")apikey: String,
        @Query("hash")hash: String,
        @Query("limit")limit: String
    ): Observable<ComicResult>
}