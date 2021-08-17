package com.r4zielchicago.android.myapplication.ui.hero

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.r4zielchicago.android.myapplication.api.entity.heroes.Hero
import com.r4zielchicago.android.myapplication.api.entity.heroes.HeroData
import com.r4zielchicago.android.myapplication.databinding.FragmentHeroBinding
import com.r4zielchicago.android.myapplication.utilities.HeroClickListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class HeroFragment: Fragment() {

    private val viewModel: HeroViewModel by viewModel()
    private val sharedPreferences: SharedPreferences by inject()
    private val gson: Gson by inject()


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

        if (getCachedList().isEmpty()) {
            viewModel.fetchHeroes()
        } else {
            viewModel.heroLiveData.value = getCachedList()
        }
        observeViewModel()
    }

    override fun onStop() {
        super.onStop()
        viewModel.heroLiveData.removeObservers(viewLifecycleOwner)
    }

    private fun observeViewModel() {
        viewModel.heroLiveData.observe(viewLifecycleOwner, {
            it?.let { heroes ->

                cacheList(heroes)

                val heroJsonObject = gson.toJson(heroes)

                val sharedPrefsEditor = sharedPreferences.edit()
                sharedPrefsEditor.putString("heroes_json", heroJsonObject)
                sharedPrefsEditor.apply()
                sharedPrefsEditor.commit()

                performSearch()
                heroAdapter.update(heroes)

                Log.i("JSON exists: ", sharedPreferences.contains("heroes_json").toString())


                Log.i("Coming From Fragment", "Character Name is: ${heroes[0].name}")
            }
        })
        binding.viewModel?.tempHeroListLiveData?.observe(viewLifecycleOwner, {
            it?.let { heroes ->

                heroAdapter.update(heroes)
            }
        })
    }

    private fun cacheList(list: List<Hero>) {
        val gson = Gson()
        val json = gson.toJson(list)//Converting List to Json
        sharedPreferences.edit()
            .putString("LIST", json)
            .apply()
    }

    private fun getCachedList(): List<Hero> {

        val gson = Gson()
        val json = sharedPreferences.getString("LIST", null)
        val type = object : TypeToken<List<Hero>>(){}.type //converting Json to List
        return gson.fromJson(json, type)
    }

    //TODO FIX SHARED PREFS
//    private fun checkSharedPrefs(): Collection<HeroResult>? {
//
//        try {
//            val heroes = sharedPreferences.getString("heroes_json", null)
//            val collectionType: Type = object : TypeToken<Collection<HeroResult?>?>() {}.type
//            val lcs: List<HeroResult> = Gson()
//                .fromJson(heroes, collectionType) as List<HeroResult>
//            val enums: Collection<HeroResult> = gson.fromJson(heroes, collectionType)
//            return lcs ?: null
//
//        }catch (exception: JsonSyntaxException){
//            throw exception
//        }
//
////        return gson.fromJson(heroes, HeroData::class.java)?: null
//    }


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