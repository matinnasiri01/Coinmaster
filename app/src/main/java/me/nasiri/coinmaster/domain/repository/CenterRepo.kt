package me.nasiri.coinmaster.domain.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import me.nasiri.coinmaster.domain.model.CoinAboutData
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.util.Resource


interface CenterRepo {

    suspend fun getNewsData(): Resource<Flow<List<FNews>>>

    suspend fun getCoinsData(): Resource<Flow<List<FCoinData>>>


    suspend fun searchAboutCoinByName(context: Context, coinName: String): Resource<CoinAboutData>

    suspend fun refresh()

}