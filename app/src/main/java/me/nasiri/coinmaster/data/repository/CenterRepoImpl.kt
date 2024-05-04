package me.nasiri.coinmaster.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.model.SCoinData
import me.nasiri.coinmaster.domain.model.convertSCoinData
import me.nasiri.coinmaster.domain.repository.CenterRepo
import me.nasiri.coinmaster.domain.repository.LocalRepo
import me.nasiri.coinmaster.domain.repository.RemoteRepo

class CenterRepoImpl(
    private val internet: Services.NetworkConnectionStatus,
    private val local: LocalRepo,
    private val remote: RemoteRepo,
) : CenterRepo {
    override fun getNewsData(): LiveData<List<FNews>> {
        return local.getNews().asLiveData()
    }

    override fun getCoinsData(): LiveData<List<SCoinData>> {
        return local.getCoins().map { it.convertSCoinData() }.asLiveData()
    }

    override suspend fun refresh(onComplete: () -> Unit) {
        if (internet.inInternetConnect()) {
            local.insertNews(remote.getNews())
            local.insertCoins(remote.getCoins())
        }
    }
}