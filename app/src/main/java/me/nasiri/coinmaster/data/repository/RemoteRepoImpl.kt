package me.nasiri.coinmaster.data.repository

import android.util.Log
import me.nasiri.coinmaster.data.remote.ApiService
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.model.fConvert
import me.nasiri.coinmaster.domain.model.lConvert
import me.nasiri.coinmaster.domain.repository.RemoteRepo
import me.nasiri.coinmaster.domain.util.Constants.TAG

class RemoteRepoImpl(private val api: ApiService) : RemoteRepo {
    override suspend fun getNews(): List<FNews> {
        try {
            Log.d(TAG, " status: ${api.getNews()!=null}")
        }catch (e:Exception){
            Log.e(TAG, e.message.toString())
        }
        return listOf()
    }

    override suspend fun getCoins(): List<FCoinData> {
        return api.getCoins().fConvert()
    }
}