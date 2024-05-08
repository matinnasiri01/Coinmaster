package me.nasiri.coinmaster.presentation.market


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.model.SCoinData
import me.nasiri.coinmaster.domain.model.convertSCoinData
import me.nasiri.coinmaster.domain.repository.CenterRepo
import me.nasiri.coinmaster.domain.util.Resource


class MarketViewModel(private val repo: CenterRepo) : ViewModel() {
    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            repo.refresh()
        }
    }


    fun news(dataCall: (List<FNews>) -> Unit) {
        viewModelScope.launch {
            when (val res = repo.getNewsData()) {
                is Resource.Success -> {
                    res.data!!.collect {
                        if (it.isNotEmpty())
                            dataCall(it)
                    }
                }

                else -> {}
            }
        }
    }


    fun coins(dataCall: (List<SCoinData>) -> Unit) {
        viewModelScope.launch {
            when (val res = repo.getCoinsData()) {
                is Resource.Success -> {
                    res.data!!.collect {
                        if (it.isNotEmpty())
                            dataCall(it.convertSCoinData())
                    }
                }

                else -> {}
            }
        }
    }
}
