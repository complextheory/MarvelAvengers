package com.r4zielchicago.android.myapplication.api.entity.comics

import com.squareup.moshi.Json

data class Thumbnail(

	@Json(name="path")
	val path: String? = null,

	@Json(name="extension")
	val extension: String? = null
)