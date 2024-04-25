package com.johnpinilla.androidmaster.noti

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceNotiView {
    @GET("v2/top-headlines")
    suspend fun getNotiView(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<NotiViewResponse>


    //@GET("/api/79a3f15aef972b7c0895050ca4a2e949/{id}")
    //suspend fun getSuperheroDetail(@Path("id") superheroId:String): Response<SuperHeroDetailResponse>
}