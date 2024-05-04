package me.nasiri.coinmaster.data.repository

import me.nasiri.coinmaster.data.remote.ApiService
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.model.fConvert
import me.nasiri.coinmaster.domain.model.lConvert
import me.nasiri.coinmaster.domain.repository.RemoteRepo

class RemoteRepoImpl(private val api: ApiService) : RemoteRepo {
    override suspend fun getNews(): List<FNews> {
        return api.getNews().lConvert()
    }

    override suspend fun getCoins(): List<FCoinData> {
        return api.getCoins().fConvert()
    }
}