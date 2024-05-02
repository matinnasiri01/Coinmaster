package me.nasiri.coinmaster.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import me.nasiri.coinmaster.di.Services

class ConnectionTester(private val context: Context) : Services.NetworkConnectionStatus {
    private val isInternetConnected: Boolean
        get() {
            var result = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val myNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                myNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                myNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                myNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
            return result
        }

    override fun inInternetConnect(): Boolean = isInternetConnected
}