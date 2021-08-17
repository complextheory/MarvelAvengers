package com.r4zielchicago.android.myapplication.ui.details.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.r4zielchicago.android.myapplication.api.entity.comics.Comic
import com.r4zielchicago.android.myapplication.api.entity.events.MarvelEvent
import com.r4zielchicago.android.myapplication.api.entity.heroes.Hero
import com.r4zielchicago.android.myapplication.api.entity.series.Series

class DetailsTabAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    private val comics: MutableList<Comic> = mutableListOf()
    private val series: MutableList<Series> = mutableListOf()
    private val events: MutableList<MarvelEvent> = mutableListOf()
    private val heroes: MutableList<Hero> = mutableListOf()

    // objects of arraylist. One is of Fragment type and
    // another one is of String type.*/
    private var fragmentList1: MutableList<Fragment> = mutableListOf()

    fun updateComics(data: List<Comic>){
        comics.clear()
        comics.addAll(data)
        notifyDataSetChanged()
    }

    fun updateSeries(data: List<Series>){
        series.clear()
        series.addAll(data)
        notifyDataSetChanged()
    }

    fun updateEvents(data: List<MarvelEvent>){
        events.clear()
        events.addAll(data)
        notifyDataSetChanged()
    }

    fun updateHeroes(data: List<Hero>) {
        heroes.clear()
        heroes.addAll(data)
        notifyDataSetChanged()
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList1[position]

    }
    override fun getItemCount(): Int {
        return fragmentList1.size
    }

    // this function adds the fragment and title in 2 separate  arraylist.
    fun addFragment(fragment: Fragment) {
        fragmentList1.add(fragment)
    }
}