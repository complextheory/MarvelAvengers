package com.r4zielchicago.android.myapplication.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.r4zielchicago.android.myapplication.Constants
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.databinding.FragmentDetailsBinding
import com.r4zielchicago.android.myapplication.ui.details.adapter.DetailsTabAdapter
import com.r4zielchicago.android.myapplication.ui.details.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.layout_details_image.*
import kotlinx.android.synthetic.main.layout_details_image.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {

    private val viewModel: DetailsViewModel by viewModel()

    lateinit var binding: FragmentDetailsBinding

    private lateinit var tabAdapter: DetailsTabAdapter

    private enum class TITLES {Comics, Series, Events}

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        // TODO Start Progress D

        tabAdapter = DetailsTabAdapter(requireActivity())

        binding.tabViewpager.adapter = tabAdapter

        setupViewPager(binding.tabViewpager)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.observeAndFetchData()
        observeViewModel()
        bindArgs(args.imagePath, args.imageExtention, args.heroName)
        val visibility = if (binding.progressBar.visibility == View.GONE) View.VISIBLE else View.GONE
        binding.progressBar.visibility = visibility
    }

    override fun onStop() {
       removeObservers()
        super.onStop()
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

                val visibility = binding.progressBar.visibility

                if (visibility == View.GONE) {
                    binding.progressBar.visibility = visibility
                }
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
        viewModel.heroesLiveData.observe(viewLifecycleOwner, {
            it?.let { heroes ->

                tabAdapter.updateHeroes(heroes)
            }
        })
    }

    private fun bindArgs(path: String?, extension: String?, name: String?) {

        binding.root.tv_hero_name.text = name

        val imageUrl = Constants.IMAGE_URL_FORMAT.format(
            path?.replace("http", "https"),
            extension
        )

        binding.apply {
            Glide.with(this.root)
                .load(imageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_marvel_logo)
                ).into(iv_thumbnail)
        }
    }

    private fun removeObservers(){
        viewModel.comicLiveData.removeObservers(viewLifecycleOwner)
        viewModel.seriesLiveData.removeObservers(viewLifecycleOwner)
        viewModel.eventsLiveData.removeObservers(viewLifecycleOwner)
    }
}