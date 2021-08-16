package com.r4zielchicago.android.myapplication.api.entity.heroes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hero (

    @Json(name = "name")
	val name : String,
    @Json(name = "description")
	val description : String,
    @Json(name = "thumbnail")
	val thumbnail : Thumbnail
)