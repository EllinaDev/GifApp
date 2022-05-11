package com.example.giphyiigroup.network.model

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("total_count")
    val total: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("offset")
    val offset: Int
)