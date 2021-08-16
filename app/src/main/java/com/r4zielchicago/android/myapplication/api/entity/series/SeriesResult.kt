package com.r4zielchicago.android.myapplication.api.entity.series

import com.squareup.moshi.Json

data class SeriesResult(

	@Json(name="data")
	val seriesData: SeriesData? = null,

	@Json(name="attributionHTML")
	val attributionHTML: String? = null,

	@Json(name="attributionText")
	val attributionText: String? = null
)