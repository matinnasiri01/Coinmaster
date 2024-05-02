package me.nasiri.coinmaster.data.remote.api

import me.nasiri.coinmaster.domain.model.NewsData
import me.nasiri.coinmaster.util.Constans.API_KEYS
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(API_KEYS)
    @GET("v2/news/")
    suspend fun getNews(
        @Query("sortOrder") sortOrder: String = "popular",
    ): NewsData


}