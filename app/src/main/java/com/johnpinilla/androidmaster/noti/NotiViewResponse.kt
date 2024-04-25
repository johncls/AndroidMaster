package com.johnpinilla.androidmaster.noti

import com.google.gson.annotations.SerializedName

class NotiViewResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val notiView:List<notiItemResponse>
)
data class notiItemResponse(
    @SerializedName("source") val source: Source,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("content") val content: String?
)
data class Source(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)