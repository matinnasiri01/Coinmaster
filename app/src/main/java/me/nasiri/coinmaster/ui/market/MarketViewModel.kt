package me.nasiri.coinmaster.ui.market


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.changeType
import me.nasiri.coinmaster.util.Constans.TAG


class MarketViewModel(private val centerRepo: Services.CenterRepo) : ViewModel(){

    fun test(){
        viewModelScope.launch {
            Log.i(TAG,centerRepo.getCoins().changeType().toString())
        }
    }
}
