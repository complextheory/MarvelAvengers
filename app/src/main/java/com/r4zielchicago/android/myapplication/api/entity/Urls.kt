package com.r4zielchicago.android.myapplication.api.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Urls (

	@Json(name = "type")
	val type : String,
	@Json(name = "url")
	val url : String
)