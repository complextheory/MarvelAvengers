package com.r4zielchicago.android.myapplication.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.api.entity.Hero
import com.r4zielchicago.android.myapplication.databinding.ItemViewHeroBinding
import com.r4zielchicago.android.myapplication.utilities.HeroClickListener

class HeroAdapter(
    private val heroListener: HeroClickListener
) : RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    private val heroes: MutableList<Hero> = mutableListOf()

    fun update(data: List<Hero>){
        heroes.clear()
        heroes.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Log.wtf("Coming From Adapter", "Adapter")

        return ViewHolder(
            ItemViewHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = heroes[position]
        holder.bind(hero)
    }

    override fun getItemCount(): Int = heroes.size

    inner class ViewHolder(private val binding: ItemViewHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            val imageUrl = IMAGE_URL_FORMAT.format(
                hero.thumbnail.path.replace("http", "https"),
                hero.thumbnail.extension
            )

            binding.apply {
                binding.heroItemClick = heroListener
                binding.hero = hero

                Glide.with(this.root)
                    .load(imageUrl)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_marvel_logo)
                    ).into(ivCharacterThumbnail)

                tvCharacterName.text = hero.name
            }
        }
    }

        companion object {
            private const val IMAGE_URL_FORMAT = "%s" + "/standard_medium" + ".%s"
        }
    }