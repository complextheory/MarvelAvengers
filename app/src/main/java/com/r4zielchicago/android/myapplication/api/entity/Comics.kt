package com.r4zielchicago.android.myapplication.api.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Comics (

	@Json(name = "available")
	val available : String,
	@Json(name = "returned")
	val returned : String,
	@Json(name = "collectionURI")
	val collectionURI : String,
	@Json(name = "items")
	val items : List<Items>
)