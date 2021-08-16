package com.r4zielchicago.android.myapplication.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4zielchicago.android.myapplication.api.entity.comics.Comic
import com.r4zielchicago.android.myapplication.repository.DetailsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins.onError
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsViewModel(private val detailsRepository: DetailsRepository) : ViewModel() {

    val comicLiveData = MutableLiveData<List<Comic>>()

    fun fetchComics() {
        detailsRepository.getComics()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val comics = it.data?.results ?: emptyList()
                    comicLiveData.value = comics

                },
                {
                    comicLiveData.value = emptyList()
                    onError(it)
                }
            )
    }

}