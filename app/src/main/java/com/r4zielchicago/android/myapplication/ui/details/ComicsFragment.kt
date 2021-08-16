package com.r4zielchicago.android.myapplication.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.ui.details.adapter.ComicsAdapter
import kotlinx.android.synthetic.main.layout_comics.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ComicsFragment: Fragment() {

    private val rvAdapter = ComicsAdapter()

    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.layout_comics, container, false)

        view.rv_comics_list.adapter = rvAdapter
        observeViewModel()


        return view
    }

    override fun onStop() {
        super.onStop()
        viewModel.comicLiveData.removeObservers(viewLifecycleOwner)
    }

    private fun observeViewModel() {
        viewModel.comicLiveData.observe(viewLifecycleOwner, {
            it?.let { heroes ->

                rvAdapter.updateComics(heroes)

            }
        })
    }
}