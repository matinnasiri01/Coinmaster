package me.nasiri.coinmaster.presentation.coin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.nasiri.coinmaster.domain.model.CoinAboutData
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.repository.CenterRepo


class CoinViewModel(private val repo: CenterRepo) : ViewModel() {

    fun findCoin(id: Long, onResult: (FCoinData) -> Unit) {
        viewModelScope.launch {
            repo.findCoinByID(id,onResult)
        }
    }

    fun findAbout(name: String, context: Context, call: (CoinAboutData) -> Unit) {
        viewModelScope.launch {
            call(repo.findAboutCoinByName(context, name)!!)
        }
    }
}