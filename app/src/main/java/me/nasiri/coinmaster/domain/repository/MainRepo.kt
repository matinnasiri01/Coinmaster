package me.nasiri.coinmaster.domain.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.CoinAboutData
import me.nasiri.coinmaster.domain.model.CusNews
import me.nasiri.coinmaster.domain.model.FCoinData

class MainRepo(
    private val localRepo: Services.LocalRepo,
    private val remoteRepo: Services.RemoteRepo,
    private val internet: Services.NetworkConnectionStatus,
) : Services.CenterRepo {
    override suspend fun getNews(): LiveData<List<CusNews>> {
        return localRepo.getNews().asLiveData()
    }


    override suspend fun getCoins(): LiveData<List<FCoinData>> {
        return localRepo.getCoins().asLiveData()
    }

    override suspend fun refreshAll() {
        if (internet.inInternetConnect()) {
            updateNews()
            updateCoins()
        }
    }


    override suspend fun updateCoins() {
        localRepo.insertCoins(remoteRepo.getCoins())
    }

    override suspend fun updateNews() {
        localRepo.insertNews(remoteRepo.getNews().toList())
    }

    override suspend fun findFullCoinDataByName(id: Long): FCoinData {
        TODO("Not yet implemented")
    }

    override suspend fun findAboutByName(id: Long): CoinAboutData {
        TODO("Not yet implemented")
    }
}