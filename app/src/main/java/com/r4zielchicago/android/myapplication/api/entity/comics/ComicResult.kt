package com.r4zielchicago.android.myapplication.api.entity.comics

import com.squareup.moshi.Json

data class ComicResult(

    @Json(name="copyright")
	val copyright: String? = null,

    @Json(name="code")
	val code: String? = null,

    @Json(name="data")
	val comicsData: ComicsData? = null,

    @Json(name="attributionHTML")
	val attributionHTML: String? = null,

    @Json(name="attributionText")
	val attributionText: String? = null,

    @Json(name="etag")
	val etag: String? = null,

    @Json(name="status")
	val status: String? = null
)