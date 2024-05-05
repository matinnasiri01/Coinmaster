package me.nasiri.coinmaster.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.google.gson.Gson
import kotlinx.coroutines.flow.map
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.CoinAboutData
import me.nasiri.coinmaster.domain.model.CoinsAboutData
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.model.SCoinData
import me.nasiri.coinmaster.domain.model.convertSCoinData
import me.nasiri.coinmaster.domain.repository.CenterRepo
import me.nasiri.coinmaster.domain.repository.LocalRepo
import me.nasiri.coinmaster.domain.repository.RemoteRepo
import me.nasiri.coinmaster.domain.util.Constants.TAG
import me.nasiri.coinmaster.domain.util.Resource
import java.lang.Exception

class CenterRepoImpl(
    private val internet: Services.NetworkConnectionStatus,
    private val local: LocalRepo,
    private val remote: RemoteRepo,
) : CenterRepo {
    override suspend fun getNewsData(): Resource<List<FNews>> {
        return try {
            Resource.Success(local.getNews())
        } catch (e: Exception) {
            Resource.Error(message = e.message)
        }
    }

    override suspend fun getCoinsData(): Resource<List<SCoinData>> {
        return try {
            Resource.Success(local.getCoins().convertSCoinData())
        } catch (e: Exception) {
            Resource.Error(message = e.message)
        }
    }

    override suspend fun getFCoinByID(id: Long): Resource<FCoinData> {
        return try {
            Resource.Success(local.getCoins().find { it.id == id }!!)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Not Found")
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
                    return Resource.Error("Not Found")
                }
            }
            return Resource.Error("Not Found")
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }

    override suspend fun refresh() {
        if (internet.inInternetConnect()) {
            local.insertNews(remote.getNews())
            Log.v(TAG, "news:Cash:\n${local.getNews()}")
            local.insertCoins(remote.getCoins())
            Log.v(TAG, "Coins:Cash:\n${local.getCoins()}")
            return
        }
        Log.e(TAG, "Check Your internet connection!")
    }
}