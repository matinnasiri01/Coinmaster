package me.nasiri.coinmaster.data.repository

import kotlinx.coroutines.flow.Flow
import me.nasiri.coinmaster.data.local.database.CoinsDao
import me.nasiri.coinmaster.data.local.database.NewsDao
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.repository.LocalRepo

class LocalRepoImpl(private val newsDao: NewsDao, private val coinsDao: CoinsDao) : LocalRepo {
    override fun getNews(): Flow<List<FNews>> {
        return newsDao.getNews()
    }

    override fun getCoins(): Flow<List<FCoinData>> {
        return coinsDao.getCoins()
    }

    override suspend fun insertNews(list: List<FNews>) {
        newsDao.insertNews(list)
    }

    override suspend fun insertCoins(list: List<FCoinData>) {
        coinsDao.insertCoins(list)
    }
}