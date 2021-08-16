package com.r4zielchicago.android.myapplication.api.entity.events

import com.squareup.moshi.Json

data class MarvelEvent(

	@Json(name="thumbnail")
	val thumbnail: Thumbnail? = null,

	@Json(name="start")
	val start: String? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="title")
	val title: String? = null
)