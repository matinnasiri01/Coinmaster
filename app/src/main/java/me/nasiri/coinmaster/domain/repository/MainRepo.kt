package me.nasiri.coinmaster.domain.repository

import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.CoinsData
import me.nasiri.coinmaster.domain.model.News
import me.nasiri.coinmaster.domain.model.toList

class MainRepo(
    private val localRepo: Services.LocalRepo,
    private val remoteRepo: Services.RemoteRepo,
) : Services.CenterRepo {
    override suspend fun getCoins(): CoinsData {
        return remoteRepo.getCoins()
    }

    override suspend fun getNews(): List<News> {
        return remoteRepo.getNews().toList()
    }

    override suspend fun updateCoins() {
        TODO("Not yet implemented")
    }

    override suspend fun updateNews() {
        TODO("Not yet implemented")
    }

    override suspend fun findAboutByName() {
        TODO("Not yet implemented")
    }
}