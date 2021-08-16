package com.r4zielchicago.android.myapplication.api.entity.heroes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroResult (

	val copyright : String,
	@Json(name = "attributionText")
	val attributionText : String,
	@Json(name = "attributionHTML")
	val attributionHTML : String,
	@Json(name = "data")
	val heroData : HeroData
)