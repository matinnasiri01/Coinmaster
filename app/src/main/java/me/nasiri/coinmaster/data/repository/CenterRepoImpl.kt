package me.nasiri.coinmaster.data.repository

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.CoinAboutData
import me.nasiri.coinmaster.domain.model.CoinsAboutData
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.repository.CenterRepo
import me.nasiri.coinmaster.domain.repository.LocalRepo
import me.nasiri.coinmaster.domain.repository.RemoteRepo

class CenterRepoImpl(
    private val internet: Services.NetworkConnectionStatus,
    private val local: LocalRepo,
    private val remote: RemoteRepo,
) : CenterRepo {
    override suspend fun getNewsData(): Flow<List<FNews>> {
        return local.getNews()
    }

    override suspend fun getFCoinsData(): Flow<List<FCoinData>> {
        return local.getCoins()
    }

    override suspend fun findCoinByID(id: Long, call: (FCoinData) -> Unit) {
        return getFCoinsData().collect { list ->
            call(list.find { it.id == id }!!)
        }
    }

    override suspend fun findAboutCoinByName(
        context: Context,
        coinName: String,
    ): CoinAboutData? {
        val file = context.assets
            .open("currencyinfo.json")
            .bufferedReader()
            .use { it.readText() }

        val gson = Gson()
        val response = gson.fromJson(file, CoinsAboutData::class.java)
        return try {
            response.forEach {
                if (it.currencyName == coinName) {
                    CoinAboutData(
                        coinWebsite = it.info!!.web,
                        coinGithub = it.info.github,
                        coinTwitter = it.info.twt,
                        coinDes = it.info.desc,
                        coinReddit = it.info.reddit
                    )
                    return null
                }
            }
            return null
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun refresh() {
        if (internet.inInternetConnect()) {
            local.insertNews(remote.getNews())
            local.insertCoins(remote.getCoins())
            return
        }
    }
}