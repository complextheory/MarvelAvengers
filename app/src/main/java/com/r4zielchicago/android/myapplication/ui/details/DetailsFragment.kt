package com.r4zielchicago.android.myapplication.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.r4zielchicago.android.myapplication.databinding.FragmentDetailsBinding
import com.r4zielchicago.android.myapplication.ui.details.adapter.DetailsTabAdapter
import com.r4zielchicago.android.myapplication.ui.details.viewModel.DetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {

    private val viewModel: DetailsViewModel by viewModel()

    lateinit var binding: FragmentDetailsBinding

    private lateinit var tabAdapter: DetailsTabAdapter

    private enum class TITLES {Comics, Series, Events}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        tabAdapter = DetailsTabAdapter(requireActivity())

        binding.tabViewpager.adapter = tabAdapter

        setupViewPager(binding.tabViewpager)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.observeAndFetchData()
        observeViewModel()
    }

    override fun onStop() {
        super.onStop()
        viewModel.comicLiveData.removeObservers(viewLifecycleOwner)
        viewModel.seriesLiveData.removeObservers(viewLifecycleOwner)
        viewModel.eventsLiveData.removeObservers(viewLifecycleOwner)
    }

    // This function is used to add items in arraylist and assign
    // the adapter to view pager
    private fun setupViewPager(viewPager: ViewPager2) {

        tabAdapter.addFragment(ComicsFragment())
        tabAdapter.addFragment(SeriesFragment())
        tabAdapter.addFragment(EventsFragment())

        viewPager.orientation = (ViewPager2.ORIENTATION_HORIZONTAL)
        viewPager.adapter = tabAdapter

        TabLayoutMediator(binding.tabLayout, binding.tabViewpager) { tab, position ->

            when(position){
                TITLES.Comics.ordinal -> {
                    tab.text = TITLES.Comics.name
                }
                TITLES.Series.ordinal -> {
                    tab.text = TITLES.Series.name
                }
                TITLES.Events.ordinal -> {
                    tab.text = TITLES.Events.name
                }
            }

        }.attach()
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