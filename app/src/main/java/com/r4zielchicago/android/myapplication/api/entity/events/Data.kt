package com.r4zielchicago.android.myapplication.api.entity.events

import com.squareup.moshi.Json

data class Data(
	@Json(name="results")
	val results: List<Event?>? = null
)