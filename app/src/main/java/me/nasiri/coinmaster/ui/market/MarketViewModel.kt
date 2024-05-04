package me.nasiri.coinmaster.ui.market


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import me.nasiri.coinmaster.di.Services


class MarketViewModel(private val centerRepo: Services.CenterRepo) : ViewModel() {

    val news = runBlocking { centerRepo.getNews() }
    fun callUpdate() {
        runBlocking {
            centerRepo.updateNews()
        }
    }
}
