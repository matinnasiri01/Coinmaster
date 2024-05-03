package me.nasiri.coinmaster.domain.repository

import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.News

class MainRepo(
    private val localRepo: Services.LocalRepo,
    private val remoteRepo: Services.RemoteRepo,
) : Services.CenterRepo {
    override suspend fun getCoins() {
        TODO("Not yet implemented")
    }

    override suspend fun getNews(): List<News> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCoins() {
        TODO("Not yet implemented")
    }

    override suspend fun updateNews() {
        TODO("Not yet implemented")
    }
}