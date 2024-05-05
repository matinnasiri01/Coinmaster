package me.nasiri.coinmaster.presentation.market


import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.model.SCoinData
import me.nasiri.coinmaster.domain.repository.CenterRepo
import me.nasiri.coinmaster.domain.util.Constants.TAG
import me.nasiri.coinmaster.domain.util.Resource


class MarketViewModel(private val repo: CenterRepo) : ViewModel() {
    fun news(dataCall: (List<FNews>) -> Unit) {
        viewModelScope.launch {
            when (val data = repo.getNewsData()) {
                is Resource.Success -> {
                    dataCall(data.data!!)
                }

                else -> {}
            }
        }
    }

    fun coins(dataCall: (List<SCoinData>) -> Unit) {
        viewModelScope.launch {
            when (val data = repo.getCoinsData()) {
                is Resource.Success -> {
                    dataCall(data.data!!)
                }

                else -> {}
            }
        }
    }


    fun testabout(c: Context) {
        viewModelScope.launch {
            when (val dataFCOIN = repo.getFCoinByID(1)) {
                is Resource.Success -> {
                    when (val about = repo.searchAboutCoinByName(c, dataFCOIN.data?.coinName!!)) {
                        is Resource.Success -> {
                            Log.i(TAG, about.data?.coinDes!!)
                        }

                        else -> {
                            Log.i(TAG, "about ${about.message!!}")
                        }
                    }
                }

                else -> {
                    Log.i(TAG, "coin ${dataFCOIN.message!!}")
                }
            }
        }
    }

}