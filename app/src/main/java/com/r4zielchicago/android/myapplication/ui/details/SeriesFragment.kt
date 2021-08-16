package com.r4zielchicago.android.myapplication.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.ui.details.adapter.SeriesAdapter
import com.r4zielchicago.android.myapplication.ui.details.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.layout_series.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class SeriesFragment: Fragment() {

    private val rvAdapter = SeriesAdapter()

    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.layout_series, container, false)

        view.rv_series_list.adapter = rvAdapter

        observeViewModel()

        return view
    }

    override fun onStop() {
        super.onStop()
        viewModel.seriesLiveData.removeObservers(viewLifecycleOwner)
    }

    private fun observeViewModel() {
        viewModel.seriesLiveData.observe(viewLifecycleOwner, {
            it?.let { series ->

                Log.i("SeriesFragment", "Should Update Series "
                        + "Data is ${series[0].title}")
                rvAdapter.update(series)

            }
        })
    }
}