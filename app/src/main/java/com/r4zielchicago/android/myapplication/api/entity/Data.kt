package com.r4zielchicago.android.myapplication.api.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Data (

	@Json(name = "offset")
	val offset : String,
	@Json(name = "limit")
	val limit : String,
	@Json(name = "total")
	val total : String,
	@Json(name = "count")
	val count : String,
	@Json(name = "results")
	val heroes : List<Hero>
)