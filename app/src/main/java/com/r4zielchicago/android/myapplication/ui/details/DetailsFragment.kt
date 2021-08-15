package com.r4zielchicago.android.myapplication.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.r4zielchicago.android.myapplication.databinding.FragmentDetailsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {

    private val viewModel: DetailsViewModel by viewModel()

    lateinit var binding: FragmentDetailsBinding

    private lateinit var adapter: DetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)


        adapter = DetailsAdapter(requireActivity().supportFragmentManager)

        setupViewPager(binding.tabViewpager)

        // If we dont use setupWithViewPager() method then
        // tabs are not used or shown when activity opened
        binding.tabTablayout.setupWithViewPager(binding.tabViewpager)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchHeroes()
        observeViewModel()
    }

    override fun onStop() {
        super.onStop()
        viewModel.heroLiveData.removeObservers(viewLifecycleOwner)
    }

    // This function is used to add items in arraylist and assign
    // the adapter to view pager
    private fun setupViewPager(viewpager: ViewPager) {

        adapter.addFragment(ComicsFragment(), "Comics")
        adapter.addFragment(SeriesFragment(), "Series")
        adapter.addFragment(EventsFragment(), "Events")

        viewpager.adapter = adapter
    }


    private fun observeViewModel() {
        viewModel.heroLiveData.observe(viewLifecycleOwner, {
            it?.let { heroes ->

                adapter.update(heroes)

                Log.i("From  DetailsFragment", "Character Name is: ${heroes[0].name},"
                        + " Character # of Comics Available in List is: ${heroes[0].comics.available},"
                        + " Character Comics Uri is: ${heroes[0].comics.collectionURI},"
                        + " Character 1st Comic Name in List is: ${heroes[0].comics.items[0].name},"
                        + " Character 1st Comic Uri in List is: ${heroes[0].comics.items[0].resourceURI},"
                        + " Character # of Series Available in List is: ${heroes[0].series.available},"
                        + " Character # of Events Available in List is: ${heroes[0].events.available}")
            }
        })
    }
}