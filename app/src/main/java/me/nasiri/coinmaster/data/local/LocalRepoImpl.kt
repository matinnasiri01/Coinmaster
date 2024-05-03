package me.nasiri.coinmaster.data.local

import me.nasiri.coinmaster.data.local.database.CoinsDao
import me.nasiri.coinmaster.data.local.database.NewsDao
import me.nasiri.coinmaster.di.Services

class LocalRepoImpl(private val newsDao: NewsDao, private val coinsDao: CoinsDao) :
    Services.LocalRepo {
    override suspend fun getNews() {
        TODO("Not yet implemented")
    }

    override suspend fun insertNews() {
        TODO("Not yet implemented")
    }

    override suspend fun getCoins() {
        TODO("Not yet implemented")
    }

    override suspend fun insertCoins() {
        TODO("Not yet implemented")
    }
}