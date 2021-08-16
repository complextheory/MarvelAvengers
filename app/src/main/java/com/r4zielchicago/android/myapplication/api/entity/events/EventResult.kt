package com.r4zielchicago.android.myapplication.api.entity.events

import com.squareup.moshi.Json

data class EventResult(

	@Json(name="data")
	val data: Data? = null,

	@Json(name="attributionHTML")
	val attributionHTML: String? = null,

	@Json(name="attributionText")
	val attributionText: String? = null
)