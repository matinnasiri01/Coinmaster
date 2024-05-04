package me.nasiri.coinmaster.data.remote

import me.nasiri.coinmaster.domain.model.ChartData
import me.nasiri.coinmaster.domain.model.CoinsData
import me.nasiri.coinmaster.domain.model.NewsData
import me.nasiri.coinmaster.domain.util.Constans.API_KEYS
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers(API_KEYS)
    @GET("v2/news/")
    suspend fun getNews(
        @Query("sortOrder") sortOrder: String = "popular",
    ): NewsData

    @Headers(API_KEYS)
    @GET("top/totalvolfull")
    suspend fun getCoins(
        @Query("tsym") tsYm: String = "USD",
    ): CoinsData


    @Headers(API_KEYS)
    @GET("{period}")
    suspend fun getChart(
        @Path("period") period: String,
        @Query("fsym") fromSymbol: String,
        @Query("limit") limit: Int,
        @Query("aggregate") aggregate: Int,
        @Query("tsym") toSymbol: String = "USD",
    ): ChartData

}