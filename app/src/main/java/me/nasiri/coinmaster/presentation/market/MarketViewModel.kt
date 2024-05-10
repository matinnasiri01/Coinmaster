package me.nasiri.coinmaster.presentation.market


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.model.SCoinData
import me.nasiri.coinmaster.domain.model.convertSCoinData
import me.nasiri.coinmaster.domain.repository.CenterRepo
import me.nasiri.coinmaster.domain.util.Constants.TAG


class MarketViewModel(private val repo: CenterRepo) : ViewModel() {
    private val mNews = MutableLiveData<List<FNews>>()
    private val mCoins = MutableLiveData<List<SCoinData>>()
    val news: LiveData<List<FNews>> = mNews
    val coins: LiveData<List<SCoinData>> = mCoins
    val refresh: Unit
        get() {
            viewModelScope.launch {
                Log.i(TAG, "Refreshing!*")
                repo.refresh()
            }
        }

    init {
        refresh
        fetchData()
    }


    private fun fetchData() {
        viewModelScope.launch {
            launch { repo.getNewsData().collect { mNews.postValue(it) } }
            repo.getFCoinsData().map { it.convertSCoinData() }.collect { mCoins.postValue(it) }
        }
    }
}
