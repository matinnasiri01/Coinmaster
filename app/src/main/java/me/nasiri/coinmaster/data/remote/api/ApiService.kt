package me.nasiri.coinmaster.data.remote.api

import me.nasiri.coinmaster.domain.model.CoinsData

interface ApiService {
    suspend fun getAllCoins(): CoinsData
}