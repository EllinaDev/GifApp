package com.example.giphyiigroup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphyiigroup.network.model.TrendingResponse
import com.example.giphyiigroup.repo.GifsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var repository: GifsRepository) : ViewModel() {
    val disposables = CompositeDisposable()
    val trendingLiveData = MutableLiveData<TrendingResponse>()
    val errorLiveData = MutableLiveData<Throwable>()

    fun getTrendingGifs(limit: Int, offset: Int) {
        disposables.add(
            repository.getTrending(Constants.API_KEY, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    trendingLiveData.postValue(it)
                }, {
                    errorLiveData.postValue(it)
                })
        )
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}