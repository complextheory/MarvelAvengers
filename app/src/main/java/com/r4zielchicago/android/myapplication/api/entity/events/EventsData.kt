package com.r4zielchicago.android.myapplication.api.entity.events

import com.squareup.moshi.Json

data class EventsData(
	@Json(name="results")
	val results: List<MarvelEvent>? = null
)