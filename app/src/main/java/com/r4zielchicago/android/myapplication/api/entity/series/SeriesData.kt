package com.r4zielchicago.android.myapplication.api.entity.series

import com.squareup.moshi.Json

data class SeriesData(

	@Json(name="total")
	val total: String? = null,

	@Json(name="offset")
	val offset: String? = null,

	@Json(name="limit")
	val limit: String? = null,

	@Json(name="count")
	val count: String? = null,

	@Json(name="results")
	val results: List<Series>? = null
)