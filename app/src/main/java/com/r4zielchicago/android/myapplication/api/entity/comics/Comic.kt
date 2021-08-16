package com.r4zielchicago.android.myapplication.api.entity.comics

import com.squareup.moshi.Json

data class Comic(

	@Json(name="description")
	val description: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="thumbnail")
	val thumbnail: Thumbnail? = null
)