package com.r4zielchicago.android.myapplication.api.entity.local

import com.r4zielchicago.android.myapplication.api.entity.comics.ComicResult
import com.r4zielchicago.android.myapplication.api.entity.events.EventResult
import com.r4zielchicago.android.myapplication.api.entity.series.SeriesResult

data class LocalDetails(val comicResult: ComicResult,
                        val seriesResult: SeriesResult,
                        val eventResult: EventResult)
