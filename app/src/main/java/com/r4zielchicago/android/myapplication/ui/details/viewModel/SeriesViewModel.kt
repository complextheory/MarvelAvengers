package com.r4zielchicago.android.myapplication.ui.details.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4zielchicago.android.myapplication.api.entity.series.Series
import com.r4zielchicago.android.myapplication.repository.DetailsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers

class SeriesViewModel(private val detailsRepository: DetailsRepository): ViewModel() {

    val seriesLiveData = MutableLiveData<List<Series>>()

    fun fetchSeries() {
        detailsRepository.getSeries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val series = it.seriesData?.results ?: emptyList()
                    seriesLiveData.value = series

                },
                {
                    seriesLiveData.value = emptyList()
                    RxJavaPlugins.onError(it)
                }
            )
    }

}