package com.example.giphyiigroup.network

import com.example.giphyiigroup.network.model.TrendingResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("trending")
    fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<TrendingResponse>
}