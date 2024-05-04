package me.nasiri.coinmaster.domain.repository

import androidx.lifecycle.LiveData
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.model.SCoinData


interface CenterRepo {

    fun getNewsData(): LiveData<List<FNews>>

    fun getCoinsData(): LiveData<List<SCoinData>>

    suspend fun refresh(onComplete: () -> Unit)

    /* todo make another function for this interface*/

}