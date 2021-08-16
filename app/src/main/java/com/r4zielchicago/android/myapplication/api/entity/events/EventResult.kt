package com.r4zielchicago.android.myapplication.api.entity.events

import com.squareup.moshi.Json

data class EventResult(

	@Json(name="data")
	val eventsData: EventsData? = null,

	@Json(name="attributionHTML")
	val attributionHTML: String? = null,

	@Json(name="attributionText")
	val attributionText: String? = null,

	@Json(name="etag")
	val etag: String? = null,

	@Json(name="status")
	val status: String? = null
)