package me.nasiri.coinmaster.data.remote

import me.nasiri.coinmaster.data.remote.api.ApiService
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.CusNews
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.fConvert
import me.nasiri.coinmaster.domain.model.lConvert

class RemoteRepoImpl(private val api: ApiService) : Services.RemoteRepo {
    override suspend fun getNews(): List<CusNews> = api.getNews().lConvert()

    override suspend fun getCoins(): List<FCoinData> = api.getCoins().fConvert()

    override suspend fun getChart() {}
}