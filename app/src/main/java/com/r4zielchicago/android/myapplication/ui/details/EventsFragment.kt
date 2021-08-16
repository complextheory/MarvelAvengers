package com.r4zielchicago.android.myapplication.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.ui.details.adapter.EventsAdapter
import com.r4zielchicago.android.myapplication.ui.details.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.layout_events.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class EventsFragment: Fragment() {

    private val rvAdapter = EventsAdapter()

    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.layout_events, container, false)

        view.rv_events_list.adapter = rvAdapter

        observeViewModel()

        return view
    }

    override fun onStop() {
        super.onStop()
        viewModel.eventsLiveData.removeObservers(viewLifecycleOwner)
    }

    private fun observeViewModel() {
        viewModel.eventsLiveData.observe(viewLifecycleOwner, {
            it?.let { events ->

                rvAdapter.update(events)
            }
        })
    }
}