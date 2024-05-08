package me.nasiri.coinmaster.domain.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import me.nasiri.coinmaster.domain.model.CoinAboutData
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.FNews


interface CenterRepo {

    suspend fun getNewsData(): Flow<List<FNews>>

    suspend fun getCoinsData(): Flow<List<FCoinData>>


    suspend fun searchAboutCoinByName(context: Context, coinName: String): CoinAboutData?

    suspend fun refresh()

}