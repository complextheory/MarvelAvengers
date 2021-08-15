package com.r4zielchicago.android.myapplication.ui.details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.api.entity.Hero
import com.r4zielchicago.android.myapplication.databinding.ItemViewDetailsBinding
import java.lang.IllegalArgumentException

class DetailsRVAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val heroes: MutableList<Hero> = mutableListOf()


    enum class HeroTypes {Comics, Series, Events}

    fun update(data: List<Hero>){
        heroes.clear()
        heroes.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        Log.i("Coming From Adapter", "Adapter")

        return when(viewType) {
            HeroTypes.Comics.ordinal -> {ComicsViewHolder(
                ItemViewDetailsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )}

//            HeroTypes.Series.ordinal -> {
//
//            }
//
//            HeroTypes.Events.ordinal -> {
//
//            }
            else ->
                throw IllegalArgumentException("ViewType Unknown")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hero = heroes[position]

        when(getItemViewType(position)){
            HeroTypes.Comics.ordinal -> {
                holder as ComicsViewHolder
                holder.bind(hero)
            }
            1 -> {

            }
            2 -> {

            }

        }
    }

    override fun getItemCount(): Int = heroes.size


    inner class ComicsViewHolder(private val binding: ItemViewDetailsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: Hero) {

//            val imageUrl = IMAGE_URL_FORMAT.format(
//                hero.thumbnail.path.replace("http", "https"),
//                hero.thumbnail.extension
//            )

            Log.i("ComicsViewHolder", "Comic 0 ResourceUri is: ${hero.comics.items[0].resourceURI}"
                    + " The Name is: ${hero.comics.items[0].name}")
//            val imageUrl = IMAGE_URL_FORMAT.format(
//                hero.comics.items[0].resourceURI
//            )

            binding.apply {

//                Glide.with(this.root)
//                    .load(imageUrl)
//                    .apply(
//                        RequestOptions()
//                            .placeholder(R.drawable.ic_marvel_logo)
//                    ).into(ivThumbnail)
//
//                tvDescription.text = hero.name
            }
        }
    }

    companion object {
        private const val IMAGE_URL_FORMAT = "%s" + "/standard_medium" + ".%s"
    }


}