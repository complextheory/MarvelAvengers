package com.r4zielchicago.android.myapplication.ui.details.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.r4zielchicago.android.myapplication.Constants
import com.r4zielchicago.android.myapplication.R
import com.r4zielchicago.android.myapplication.api.entity.series.Series
import com.r4zielchicago.android.myapplication.databinding.ItemViewDetailsBinding

class SeriesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val series: MutableList<Series> = mutableListOf()

    fun update(data: List<Series>) {
        series.clear()
        series.addAll(data)
        notifyDataSetChanged()
        Log.wtf("RVAdapter", "Update Series "
                + "Data is ${data[0].title}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return SeriesViewHolder( ItemViewDetailsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val marvelSeries: Series = series[position]
        holder as SeriesViewHolder
        holder.bind(marvelSeries)
    }

    override fun getItemCount(): Int = series.size

    inner class SeriesViewHolder(private val binding: ItemViewDetailsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(series: Series) {

            val imageUrl = Constants.IMAGE_URL_FORMAT.format(
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
}