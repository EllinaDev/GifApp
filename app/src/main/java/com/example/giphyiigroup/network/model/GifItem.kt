package com.example.giphyiigroup.network.model

import com.google.gson.annotations.SerializedName

data class GifItem(
    val id: String,
    val title: String,
    val url: String,
    val images: Images
)

data class Images(
    @SerializedName("original")
    val original: ImageItem,
    @SerializedName("downsized_medium")
    val downsized: ImageItem
)

data class ImageItem(
    val url: String
)