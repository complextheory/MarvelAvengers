package com.r4zielchicago.android.myapplication.utilities

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.r4zielchicago.android.myapplication.api.entity.heroes.HeroData

const val heroKey = "HERO_KEY"
const val comicsKey = "COMICS_KEY"
const val seriesKey = "SERIES_KEY"
const val eventsKey = "EVENTS_KEY"
interface SharedPrefsUtil {
    fun <T: Any>saveToPrefs(list: List<T>, key: String)
    fun <T: Any>getFromPrefs(key: String): List<T>
}

class SharedPrefsUtilImpl(context: Context): SharedPrefsUtil {

    private val sharedPreferences = context.getSharedPreferences(
        "MARVEL_SHARED_PREFS",
        Context.MODE_PRIVATE
    )

    override fun <T: Any> saveToPrefs(list: List<T>, key: String) {
        val gson = Gson()
        val json = gson.toJson(list)//Converting List to Json
        sharedPreferences.edit()
            .putString(key, json)
            .apply()
    }

    override fun <T: Any> getFromPrefs(key: String): List<T> {
        val gson = Gson()
        val json = sharedPreferences.getString(key, "")
        val type = object : TypeToken<List<T>>(){}.type //converting Json to List
        return gson.fromJson(json, type) ?: emptyList()
    }
}