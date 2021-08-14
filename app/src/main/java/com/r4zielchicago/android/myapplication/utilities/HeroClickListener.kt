package com.r4zielchicago.android.myapplication.utilities

import com.r4zielchicago.android.myapplication.api.entity.Hero

interface HeroClickListener {
    fun onHeroClicked(hero: Hero)
}