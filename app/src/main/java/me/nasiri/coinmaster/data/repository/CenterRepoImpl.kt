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
import me.nasiri.coinmaster.domain.util.Errors
import me.nasiri.coinmaster.domain.util.Resource

class CenterRepoImpl(
    private val internet: Services.NetworkConnectionStatus,
    private val local: LocalRepo,
    private val remote: RemoteRepo,
) : CenterRepo {
    override suspend fun getNewsData(): Resource<Flow<List<FNews>>> {
        return try {
            Resource.Success(local.getNews())
        } catch (e: Exception) {
            Resource.Error(Errors.OTHER)
        }
    }

    override suspend fun getCoinsData(): Resource<Flow<List<FCoinData>>> {
        return try {
            Resource.Success(local.getCoins())
        } catch (e: Exception) {
            Resource.Error(Errors.OTHER)
        }
    }

    override suspend fun searchAboutCoinByName(
        context: Context,
        coinName: String,
    ): Resource<CoinAboutData> {
        val file = context.assets
            .open("currencyinfo.json")
            .bufferedReader()
            .use { it.readText() }

        val gson = Gson()
        val response = gson.fromJson(file, CoinsAboutData::class.java)
        return try {
            response.forEach {
                if (it.currencyName == coinName) {
                    Resource.Success(
                        CoinAboutData(
                            coinWebsite = it.info!!.web,
                            coinGithub = it.info.github,
                            coinTwitter = it.info.twt,
                            coinDes = it.info.desc,
                            coinReddit = it.info.reddit
                        )
                    )
                    return Resource.Error(Errors.FOUND)
                }
            }
            return Resource.Error(Errors.FOUND)
        } catch (e: Exception) {
            Resource.Error(Errors.OTHER)
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