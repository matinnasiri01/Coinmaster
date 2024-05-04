package me.nasiri.coinmaster.data.local

import kotlinx.coroutines.flow.Flow
import me.nasiri.coinmaster.data.local.database.CoinsDao
import me.nasiri.coinmaster.data.local.database.NewsDao
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.CusNews
import me.nasiri.coinmaster.domain.model.FCoinData

class LocalRepoImpl(
    private val newsDao: NewsDao,
    private val coinsDao: CoinsDao,
) : Services.LocalRepo {
    override suspend fun getNews(): Flow<List<CusNews>> {
        return newsDao.getNews()
    }

    override suspend fun insertNews(list: List<CusNews>) {
        newsDao.insertNews(list)
    }

    override suspend fun getCoins(): Flow<List<FCoinData>> {
        return coinsDao.getCoins()
    }

    override suspend fun insertCoins(list: List<FCoinData>) {
        coinsDao.insertCoins(list)
    }
}