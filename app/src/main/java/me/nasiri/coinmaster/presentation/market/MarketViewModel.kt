package me.nasiri.coinmaster.presentation.market


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.nasiri.coinmaster.domain.repository.CenterRepo
import me.nasiri.coinmaster.domain.util.Constans.TAG


class MarketViewModel(private val repo: CenterRepo) : ViewModel() {
    val news = repo.getNewsData()
    val coins = repo.getCoinsData()

    init {
        viewModelScope.launch {
            repo.refresh {
                Log.v(TAG, "${news.value}\n ${coins.value}")
            }
        }
    }


}