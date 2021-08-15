package com.r4zielchicago.android.myapplication.ui.hero

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.api.entity.Hero
import com.r4zielchicago.android.myapplication.databinding.FragmentHeroBinding
import com.r4zielchicago.android.myapplication.utilities.HeroClickListener
import org.koin.android.viewmodel.ext.android.viewModel

class HeroFragment: Fragment() {

    private val viewModel: HeroViewModel by viewModel()
    private lateinit var binding: FragmentHeroBinding

    private var matchedHeroList = mutableListOf<Hero>()

    private val heroItemClickListener = object: HeroClickListener {
        override fun onHeroClicked(hero: Hero) {

            binding.root.findNavController().navigate(R.id.action_heroFragment_to_detailsFragment)
            //TODO Remember requireContext()/ requireActivity()
            Toast.makeText(requireContext(), hero.name, Toast.LENGTH_SHORT).show()
        }
    }

    private val heroAdapter = HeroAdapter(heroListener = heroItemClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHeroBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        binding.rvCharacterList.adapter = heroAdapter
        binding.characterSearchView.isSubmitButtonEnabled = true

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
        viewModel.heroLiveData.observe(viewLifecycleOwner, {
            it?.let { heroes ->

                performSearch()
                heroAdapter.update(heroes)

                Log.i("Coming From Fragment", "Character Name is: ${heroes[0].name},"
                        + " Character # of Comics Available in List is: ${heroes[0].comics.available},"
                        + " Character Comics Uri is: ${heroes[0].comics.collectionURI},"
                        + " Character 1st Comic Name in List is: ${heroes[0].comics.items[0].name},"
                        + " Character 1st Comic Uri in List is: ${heroes[0].comics.items[0].resourceURI},"
                        + " Character # of Series Available in List is: ${heroes[0].series.available},"
                        + " Character # of Events Available in List is: ${heroes[0].events.available}")
            }
        })
        binding.viewModel?.tempHeroListLiveData?.observe(viewLifecycleOwner, {
            it?.let { heroes ->

                heroAdapter.update(heroes)
            }
        })
    }

    private fun performSearch() {
        binding.characterSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }
        })
    }

    private fun search(text: String?) {
        matchedHeroList = mutableListOf()

        //TODO Limit to searches where the query length is > 0

        text?.let {
            viewModel.heroList.forEach { hero ->
                if (hero.name.contains(text, true)){
                    matchedHeroList.add(hero)
                }
            }
            if (matchedHeroList.isEmpty()) {
                Toast.makeText(requireContext(), "No match found!", Toast.LENGTH_SHORT).show()
            }
            heroAdapter.update(matchedHeroList)
        }
    }
}