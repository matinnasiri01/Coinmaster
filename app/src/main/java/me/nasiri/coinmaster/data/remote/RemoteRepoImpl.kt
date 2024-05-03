package me.nasiri.coinmaster.data.remote

import me.nasiri.coinmaster.data.remote.api.ApiService
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.CoinsData
import me.nasiri.coinmaster.domain.model.NewsData

class RemoteRepoImpl(private val api: ApiService) : Services.RemoteRepo {
    override suspend fun getNews(): NewsData = api.getNews()

    override suspend fun getCoins(): CoinsData = api.getCoins()

    override suspend fun getChart() {}
}