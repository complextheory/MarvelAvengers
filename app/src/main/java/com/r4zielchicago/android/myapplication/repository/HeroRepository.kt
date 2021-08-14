package com.r4zielchicago.android.myapplication.repository

import com.r4zielchicago.android.myapplication.Constants
import com.r4zielchicago.android.myapplication.api.HeroesApi
import com.r4zielchicago.android.myapplication.api.entity.MarvelResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HeroRepository(private val heroesApi: HeroesApi) {

    fun getHeroes(): Observable<MarvelResult> = heroesApi.getHeroes(
        ts = Constants.ts,
        apikey = Constants.API_KEY,
        hash = Constants.hash(),
        limit = Constants.limit
    )
}