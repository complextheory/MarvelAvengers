package com.r4zielchicago.android.myapplication.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.r4zielchicago.android.myapplication.Constants
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.api.entity.comics.Comic
import com.r4zielchicago.android.myapplication.databinding.ItemViewDetailsBinding

class ComicsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val comics: MutableList<Comic> = mutableListOf()

    fun update(data: List<Comic>){
        comics.clear()
        comics.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ComicsViewHolder( ItemViewDetailsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val comic: Comic = comics[position]
        holder as ComicsViewHolder
        holder.bind(comic)
    }

    override fun getItemCount(): Int = comics.size


    inner class ComicsViewHolder(private val binding: ItemViewDetailsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: Comic) {

            val imageUrl = Constants.IMAGE_URL_FORMAT.format(
                comic.thumbnail?.path?.replace("http", "https"),
                comic.thumbnail?.extension
            )

            binding.apply {

                Glide.with(this.root)
                    .load(imageUrl)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_marvel_logo)
                    ).into(ivThumbnail)

                tvDescription.text = comic.title
            }
        }
    }
}