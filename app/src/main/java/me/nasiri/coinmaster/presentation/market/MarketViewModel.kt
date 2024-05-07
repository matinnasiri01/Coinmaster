package me.nasiri.coinmaster.presentation.market


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.model.SCoinData
import me.nasiri.coinmaster.domain.repository.CenterRepo
import me.nasiri.coinmaster.domain.util.Resource


class MarketViewModel(private val repo: CenterRepo) : ViewModel() {


    fun refresh() {
        viewModelScope.launch {
            repo.refresh()
        }
    }

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

}