package com.r4zielchicago.android.myapplication.api.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hero (

	@Json(name = "id")
	val id : String,
	@Json(name = "name")
	val name : String,
	@Json(name = "description")
	val description : String,
	@Json(name = "modified")
	val modified : String,
	@Json(name = "thumbnail")
	val thumbnail : Thumbnail,
	@Json(name = "resourceURI")
	val resourceURI : String,
	@Json(name = "comics")
	val comics : Comics,
	@Json(name = "urls")
	val urls : List<Urls>,
	@Json(name = "stories")
	val stories : Stories,
	@Json(name = "events")
	val events : Events,
	@Json(name = "series")
	val series : Series
)