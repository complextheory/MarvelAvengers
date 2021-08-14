package com.r4zielchicago.android.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.r4zielchicago.android.myapplication.api.HeroesApi
import com.r4zielchicago.android.myapplication.api.entity.Hero
import com.r4zielchicago.android.myapplication.api.entity.MarvelResult
import com.r4zielchicago.android.myapplication.databinding.FragmentHeroBinding
import com.r4zielchicago.android.myapplication.factory.ViewModelFactory
import com.r4zielchicago.android.myapplication.network.NetworkService
import com.r4zielchicago.android.myapplication.utilities.HeroClickListener

class HeroFragment: Fragment() {

    private lateinit var viewModel: HeroViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentHeroBinding

    private val networkService = NetworkService()
    private val retrofit = networkService.getRetrofitInstance()
    private val characterApi = retrofit.create(HeroesApi::class.java)
    private lateinit var matchedResultList: MutableList<MarvelResult>


    private val characterItemClickListener = object: HeroClickListener {
        override fun onHeroClicked(hero: Hero) {

            //TODO Remember requireContext()/ requireActivity()
            Toast.makeText(requireContext(), hero.name, Toast.LENGTH_SHORT).show()
        }
    }

    private val heroAdapter = HeroAdapter(heroListener = characterItemClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //TODO Move out
        viewModelFactory = ViewModelFactory(characterApi)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HeroViewModel::class.java)

        binding = FragmentHeroBinding.inflate(inflater, container, false)

        binding.rvCharacterList.adapter = heroAdapter

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

    private fun observeViewModel() {
        viewModel.heroLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { heroes ->

                heroAdapter.update(heroes)

                Log.wtf("Coming From Fragment", "Character Name is: ${heroes[0].name},"
                        + " Character # of Comics Available in List is: ${heroes[0].comics.available},"
                        + " Character Comics Uri is: ${heroes[0].comics.collectionURI},"
                        + " Character 1st Comic Name in List is: ${heroes[0].comics.items[0].name},"
                        + " Character 1st Comic Uri in List is: ${heroes[0].comics.items[0].resourceURI},"
                        + " Character # of Series Available in List is: ${heroes[0].series.available},"
                        + " Character # of Events Available in List is: ${heroes[0].events.available}")
            }
        })
    }

    companion object {
        fun newInstance() = HeroFragment()
    }
}