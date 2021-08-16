package com.r4zielchicago.android.myapplication.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.ui.hero.HeroViewModel
import kotlinx.android.synthetic.main.layout_comics.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ComicsFragment: Fragment() {

    private val rvAdapter = DetailsRVAdapter()

    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.layout_comics, container, false)

        view.rv_character_list.adapter = rvAdapter

        return view
    }

    override fun onStart() {
        super.onStart()
        observeViewModel()
    }

    override fun onStop() {
        super.onStop()
        viewModel.comicLiveData.removeObservers(viewLifecycleOwner)
    }

    private fun observeViewModel() {
        viewModel.comicLiveData.observe(viewLifecycleOwner, {
            it?.let { heroes ->

                rvAdapter.update(heroes)

//                Log.i("From  ComicsFragment", "Character Name is: ${heroes[0].name},"
//                        + " Character # of Comics Available in List is: ${heroes[0].comics.available},"
//                        + " Character Comics Uri is: ${heroes[0].comics.collectionURI},"
//                        + " Character 1st Comic Name in List is: ${heroes[0].comics.items[0].name},"
//                        + " Character 1st Comic Uri in List is: ${heroes[0].comics.items[0].resourceURI},"
//                        + " Character # of Series Available in List is: ${heroes[0].series.available},"
//                        + " Character # of Events Available in List is: ${heroes[0].events.available}")
            }
        })
    }
}