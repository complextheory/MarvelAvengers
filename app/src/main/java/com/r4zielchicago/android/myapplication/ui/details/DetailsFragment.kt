package com.r4zielchicago.android.myapplication.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.r4zielchicago.android.myapplication.databinding.FragmentDetailsBinding
import com.r4zielchicago.android.myapplication.ui.details.adapter.DetailsTabAdapter
import com.r4zielchicago.android.myapplication.ui.details.viewModel.DetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {

    private val viewModel: DetailsViewModel by viewModel()

    lateinit var binding: FragmentDetailsBinding

    private lateinit var tabAdapter: DetailsTabAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)


        tabAdapter = DetailsTabAdapter(requireActivity().supportFragmentManager)

        setupViewPager(binding.tabViewpager)

        // If we dont use setupWithViewPager() method then
        // tabs are not used or shown when activity opened
        binding.tabTablayout.setupWithViewPager(binding.tabViewpager)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }

    override fun onStop() {
        super.onStop()
        viewModel.comicLiveData.removeObservers(viewLifecycleOwner)
        viewModel.seriesLiveData.removeObservers(viewLifecycleOwner)
        viewModel.eventsLiveData.removeObservers(viewLifecycleOwner)
    }

    // This function is used to add items in arraylist and assign
    // the adapter to view pager
    private fun setupViewPager(viewpager: ViewPager) {

        tabAdapter.addFragment(ComicsFragment(), "Comics")
        tabAdapter.addFragment(SeriesFragment(), "Series")
        tabAdapter.addFragment(EventsFragment(), "Events")

        viewpager.adapter = tabAdapter
    }

    private fun observeViewModel() {
        viewModel.comicLiveData.observe(viewLifecycleOwner, {
            it?.let { comics ->

                tabAdapter.updateComics(comics)
            }
        })

        viewModel.seriesLiveData.observe(viewLifecycleOwner, {
            it?.let { series ->

                tabAdapter.updateSeries(series)
            }
        })

        viewModel.eventsLiveData.observe(viewLifecycleOwner, {
            it?.let { events ->

                tabAdapter.updateEvents(events)
            }
        })
    }
}