package com.r4zielchicago.android.myapplication.ui.details.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4zielchicago.android.myapplication.api.entity.events.MarvelEvent
import com.r4zielchicago.android.myapplication.repository.DetailsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers

class EventsViewModel(private val detailsRepository: DetailsRepository): ViewModel() {

    val eventsLiveData = MutableLiveData<List<MarvelEvent>>()

    fun fetchEvents() {
        detailsRepository.getEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val events = it.eventsData?.results ?: emptyList()
                    eventsLiveData.value = events
                },
                {
                    eventsLiveData.value = emptyList()
                    RxJavaPlugins.onError(it)
                }
            )
    }
}