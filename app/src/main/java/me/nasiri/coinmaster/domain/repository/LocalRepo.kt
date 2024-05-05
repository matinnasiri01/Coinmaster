package me.nasiri.coinmaster.domain.repository

import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.FNews

interface LocalRepo {
    suspend fun getNews(): List<FNews>

    suspend fun getCoins(): List<FCoinData>

    suspend fun insertNews(list: List<FNews>)

    suspend fun insertCoins(list: List<FCoinData>)


}