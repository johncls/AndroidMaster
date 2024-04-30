package com.johnpinilla.androidmaster.noti

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceNotiView {
    @GET("v2/everything")
    suspend fun getNotiView(
        @Query("q") q: String,
        @Query("language") language: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Response<NotiViewResponse>


    //@GET("/api/79a3f15aef972b7c0895050ca4a2e949/{id}")
    //suspend fun getSuperheroDetail(@Path("id") superheroId:String): Response<SuperHeroDetailResponse>
}