package me.nasiri.coinmaster.di


import android.widget.ImageView
import me.nasiri.coinmaster.domain.model.CoinsData
import me.nasiri.coinmaster.domain.model.News
import me.nasiri.coinmaster.domain.model.NewsData


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
        suspend fun getNews(): NewsData
        suspend fun getCoins(): CoinsData
        suspend fun getChart()
    }

    interface CenterRepo {
        suspend fun getNews(): List<News>
        suspend fun getCoins(): CoinsData
        suspend fun updateCoins()
        suspend fun updateNews()
        suspend fun findAboutByName()
    }
}