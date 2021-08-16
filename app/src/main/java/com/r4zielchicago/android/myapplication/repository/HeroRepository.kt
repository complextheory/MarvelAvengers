package com.r4zielchicago.android.myapplication.repository

import com.r4zielchicago.android.myapplication.Constants
import com.r4zielchicago.android.myapplication.api.MarvelApi
import com.r4zielchicago.android.myapplication.api.entity.heroes.HeroResult
import io.reactivex.rxjava3.core.Observable

class HeroRepository(private val marvelApi: MarvelApi) {

    fun getHeroes(): Observable<HeroResult> = marvelApi.getHeroes(
        ts = Constants.ts,
        apikey = Constants.API_KEY,
        hash = Constants.hash(),
        limit = Constants.limit
    )
}