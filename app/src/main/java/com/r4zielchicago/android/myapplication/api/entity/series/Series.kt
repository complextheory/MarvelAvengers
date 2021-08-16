package com.r4zielchicago.android.myapplication.api.entity.series

import com.squareup.moshi.Json

data class Series(

	@Json(name="thumbnail")
	val thumbnail: Thumbnail? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="title")
	val title: String? = null
)