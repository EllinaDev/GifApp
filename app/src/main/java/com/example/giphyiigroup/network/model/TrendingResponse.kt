package com.example.giphyiigroup.network.model

data class TrendingResponse(
    val data: List<GifItem>,
    val pagination: Pagination
)