package me.nasiri.coinmaster.domain.repository

import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.model.FNews

interface RemoteRepo {

    suspend fun getNews(): List<FNews>

    suspend fun getCoins(): List<FCoinData>


    /*todo add this to project!*/

    // suspend fun getChart(): List<FNews>
}