package me.nasiri.coinmaster.domain.repository

import android.content.Context
import me.nasiri.coinmaster.domain.model.CoinAboutData
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.model.SCoinData
import me.nasiri.coinmaster.domain.util.Resource


interface CenterRepo {

    suspend fun getNewsData(): Resource<List<FNews>>

    suspend fun getCoinsData(): Resource<List<SCoinData>>

    suspend fun getFCoinByID(id: Long): Resource<FCoinData>

    suspend fun searchAboutCoinByName(context: Context, coinName: String): Resource<CoinAboutData>

    suspend fun refresh()


    /* todo make another function for this interface*/

}