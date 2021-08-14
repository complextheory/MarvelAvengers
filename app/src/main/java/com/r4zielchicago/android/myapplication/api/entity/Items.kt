package com.r4zielchicago.android.myapplication.api.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Items (

	@Json(name = "resourceURI")
	val resourceURI : String,
	@Json(name = "name")
	val name : String
)