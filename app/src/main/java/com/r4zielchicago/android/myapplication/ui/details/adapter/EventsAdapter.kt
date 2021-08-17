package com.r4zielchicago.android.myapplication.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.r4zielchicago.android.myapplication.Constants
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.api.entity.events.MarvelEvent
import com.r4zielchicago.android.myapplication.databinding.ItemViewDetailsBinding

class EventsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private val events: MutableList<MarvelEvent> = mutableListOf()

    fun update(data: List<MarvelEvent>) {
        events.clear()
        events.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return EventsViewHolder( ItemViewDetailsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val marvelEvent: MarvelEvent = events[position]
        holder as EventsViewHolder
        holder.bind(marvelEvent)
    }

    override fun getItemCount(): Int = events.size

    inner class EventsViewHolder(private val binding: ItemViewDetailsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(event: MarvelEvent) {

            val imageUrl = Constants.IMAGE_URL_FORMAT.format(
                event.thumbnail?.path?.replace("http", "https"),
                event.thumbnail?.extension
            )

            binding.apply {

                Glide.with(this.root)
                    .load(imageUrl)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_marvel_logo)
                    ).into(ivThumbnail)

                tvDescription.text = event.title
            }
        }
    }
}