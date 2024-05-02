package me.nasiri.coinmaster.ui.market

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.util.Constans.TAG

class MarketViewModel(private val remoteRepo: Services.RemoteRepo) : ViewModel() {
    fun send() {
        viewModelScope.launch {
            Log.e(
                TAG,
                remoteRepo.getNews().toString()
            )
        }
    }
}