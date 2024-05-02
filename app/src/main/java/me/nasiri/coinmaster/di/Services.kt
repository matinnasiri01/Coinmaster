package me.nasiri.coinmaster.di


import android.widget.ImageView


interface Services {
    interface ImageLoader {
        fun loader(url: String, view: ImageView)
    }

    interface NetworkConnectionStatus {
        fun inInternetConnect(): Boolean
    }

    interface LocalRepo {
        suspend fun getNews()
        suspend fun insertNews()

        suspend fun getCoins()
        suspend fun insertCoins()
    }

    interface RemoteRepo {
        suspend fun getgNews()
        suspend fun getCoins()
    }

    interface CenterRepo {
        suspend fun getCoins()
        suspend fun getNews()
        suspend fun updateCoins()
        suspend fun updateNews()
    }
}