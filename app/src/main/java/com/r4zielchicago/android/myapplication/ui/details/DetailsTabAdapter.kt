package com.r4zielchicago.android.myapplication.ui.details

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.r4zielchicago.android.myapplication.api.entity.heroes.Hero

class DetailsTabAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {

    private val heroes: MutableList<Hero> = mutableListOf()

    fun update(data: List<Hero>){
        heroes.clear()
        heroes.addAll(data)
        notifyDataSetChanged()
    }

    // objects of arraylist. One is of Fragment type and
    // another one is of String type.*/
    private var fragmentList1: MutableList<Fragment> = mutableListOf()
    private var fragmentTitleList1: MutableList<String> = mutableListOf()

    // returns which item is selected from arraylist of fragments.
    override fun getItem(position: Int): Fragment {
        return fragmentList1[position]
    }

    // returns which item is selected from arraylist of titles.
    @Nullable
    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList1[position]
    }

    // returns the number of items present in arraylist.
    override fun getCount(): Int {
        return fragmentList1.size
    }

    // this function adds the fragment and title in 2 separate  arraylist.
    fun addFragment(fragment: Fragment, title: String) {
        fragmentList1.add(fragment)
        fragmentTitleList1.add(title)
    }
}