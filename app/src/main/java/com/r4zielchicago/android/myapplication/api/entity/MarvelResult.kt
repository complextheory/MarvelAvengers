package com.r4zielchicago.android.myapplication.api.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelResult (

	@Json(name = "code")
	val code : String,
	@Json(name = "status")
	val status : String,
	@Json(name = "copyright")
	val copyright : String,
	@Json(name = "attributionText")
	val attributionText : String,
	@Json(name = "attributionHTML")
	val attributionHTML : String,
	@Json(name = "etag")
	val etag : String,
	@Json(name = "data")
	val data : Data

)