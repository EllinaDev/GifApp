package com.example.giphyiigroup.repo

import com.example.giphyiigroup.network.ApiService
import com.example.giphyiigroup.network.model.TrendingResponse
import io.reactivex.Single
import javax.inject.Inject

class GifsRepository @Inject constructor(var service: ApiService) {

    fun getTrending(apiKey: String, limit: Int, offset: Int): Single<TrendingResponse> {
        return service.getTrendingGifs(apiKey, limit, offset)
    }
}