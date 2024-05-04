package me.nasiri.coinmaster.domain.repository

import kotlinx.coroutines.flow.Flow
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.FNews

interface LocalRepo {
    fun getNews(): Flow<List<FNews>>

    fun getCoins(): Flow<List<FCoinData>>

    suspend fun insertNews(list: List<FNews>)

    suspend fun insertCoins(list: List<FCoinData>)


}