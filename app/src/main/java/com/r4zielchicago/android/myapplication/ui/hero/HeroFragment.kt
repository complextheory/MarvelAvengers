package com.r4zielchicago.android.myapplication.ui.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.r4zielchicago.android.myapplication.api.entity.heroes.Hero
import com.r4zielchicago.android.myapplication.databinding.FragmentHeroBinding
import com.r4zielchicago.android.myapplication.utilities.HeroClickListener
import org.koin.android.viewmodel.ext.android.viewModel


class HeroFragment: Fragment() {

    private val viewModel: HeroViewModel by viewModel()

    private lateinit var binding: FragmentHeroBinding

    private var matchedHeroList = mutableListOf<Hero>()

    private val heroItemClickListener = object: HeroClickListener {
        override fun onHeroClicked(hero: Hero) {

            Toast.makeText(requireContext(), hero.name, Toast.LENGTH_SHORT).show()
            val directions =
                HeroFragmentDirections.actionHeroFragmentToDetailsFragment(hero.thumbnail.path, hero.thumbnail.extension, hero.name)
            binding.root.findNavController().navigate(directions)
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

    override fun onResume() {
        super.onResume()
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
            }
        })
    }

    //TODO FIX SEARCH AND SORT BUG
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