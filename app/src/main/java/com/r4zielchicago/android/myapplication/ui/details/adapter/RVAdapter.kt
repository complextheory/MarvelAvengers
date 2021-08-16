package com.r4zielchicago.android.myapplication.ui.details.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.api.entity.comics.Comic
import com.r4zielchicago.android.myapplication.api.entity.events.MarvelEvent
import com.r4zielchicago.android.myapplication.api.entity.series.Series
import com.r4zielchicago.android.myapplication.databinding.ItemViewDetailsBinding

class RVAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val comics: MutableList<Comic> = mutableListOf()
    private val series: MutableList<Series> = mutableListOf()
    private val events: MutableList<MarvelEvent> = mutableListOf()

    enum class DataTypes {Comics, Series, Events}

    fun updateComics(data: List<Comic>){
        comics.clear()
        comics.addAll(data)
        notifyDataSetChanged()
        Log.i("RVAdapter", "Update Comics")
    }

    fun updateSeries(data: List<Series>){
        series.clear()
        series.addAll(data)
        notifyDataSetChanged()
        Log.i("RVAdapter", "Update Series "
                + "Data is ${data[0].title}")
    }

    fun updateEvents(data: List<MarvelEvent>){
        events.clear()
        events.addAll(data)
        notifyDataSetChanged()
        Log.i("RVAdapter", "Update Events")
        Log.i("RVAdapter", "Update Events "
                + "Data is ${data[0].title}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        Log.i("Coming From Adapter", "Adapter")

        return when(viewType) {
            DataTypes.Comics.ordinal -> {ComicsViewHolder(
                ItemViewDetailsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )}

            DataTypes.Series.ordinal -> {SeriesViewHolder(
                ItemViewDetailsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )}

            DataTypes.Events.ordinal -> {EventsViewHolder(
                ItemViewDetailsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )}
            else ->
                throw IllegalArgumentException("ViewType Unknown")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val comic: Comic
        val marvelSeries: Series
        val event: MarvelEvent

        when(getItemViewType(position)){
            DataTypes.Comics.ordinal -> {
                comic = comics[position]
                holder as ComicsViewHolder
                holder.bind(comic)
            }
            DataTypes.Series.ordinal -> {
                marvelSeries = series[position]
                holder as SeriesViewHolder
                holder.bind(marvelSeries)
            }
            DataTypes.Events.ordinal -> {
                event = events[position]
                holder as EventsViewHolder
                holder.bind(event)
            }
        }
    }

    override fun getItemCount(): Int = comics.size


    inner class ComicsViewHolder(private val binding: ItemViewDetailsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: Comic) {

            val imageUrl = IMAGE_URL_FORMAT.format(
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

    inner class SeriesViewHolder(private val binding: ItemViewDetailsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(series: Series) {

            val imageUrl = IMAGE_URL_FORMAT.format(
                series.thumbnail?.path?.replace("http", "https"),
                series.thumbnail?.extension
            )

            Log.i("SeriesViewHolder", "Series Title is: ${series.title}")

            binding.apply {

                Glide.with(this.root)
                    .load(imageUrl)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_marvel_logo)
                    ).into(ivThumbnail)

                tvDescription.text = series.title
            }
        }
    }

    inner class EventsViewHolder(private val binding: ItemViewDetailsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(event: MarvelEvent) {

            val imageUrl = IMAGE_URL_FORMAT.format(
                event.thumbnail?.path?.replace("http", "https"),
                event.thumbnail?.extension
            )

            Log.i("EventsViewHolder", "Series Title is: ${event.title}")


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

    companion object {
        private const val IMAGE_URL_FORMAT = "%s" + "/standard_medium" + ".%s"
    }
}