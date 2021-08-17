package com.r4zielchicago.android.myapplication.ui.details.adapter

import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.r4zielchicago.android.myapplication.api.entity.comics.Comic
import com.r4zielchicago.android.myapplication.api.entity.events.MarvelEvent
import com.r4zielchicago.android.myapplication.api.entity.series.Series

class DetailsTabAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    private val comics: MutableList<Comic> = mutableListOf()
    private val series: MutableList<Series> = mutableListOf()
    private val events: MutableList<MarvelEvent> = mutableListOf()
    // objects of arraylist. One is of Fragment type and
    // another one is of String type.*/
    private var fragmentList1: MutableList<Fragment> = mutableListOf()
    private var fragmentTitleList1: MutableList<String> = mutableListOf()

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