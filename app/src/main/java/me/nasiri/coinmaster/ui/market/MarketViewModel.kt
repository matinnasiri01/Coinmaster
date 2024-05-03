package me.nasiri.coinmaster.ui.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.News

class MarketViewModel(private val centerRepo: Services.CenterRepo) : ViewModel() {
    fun getNews(onReceived: (List<News>) -> Unit) {
        viewModelScope.launch {
            onReceived(centerRepo.getNews())
        }
    }
}