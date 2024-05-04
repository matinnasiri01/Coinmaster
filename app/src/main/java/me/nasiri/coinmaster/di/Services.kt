package me.nasiri.coinmaster.di


import android.widget.ImageView
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import me.nasiri.coinmaster.domain.model.CoinAboutData
import me.nasiri.coinmaster.domain.model.CusNews
import me.nasiri.coinmaster.domain.model.FCoinData


interface Services {
    interface ImageLoader {
        fun loader(url: String, view: ImageView)
    }

    interface NetworkConnectionStatus {
        fun inInternetConnect(): Boolean
    }

    interface LocalRepo {
        suspend fun getNews(): Flow<List<CusNews>>
        suspend fun insertNews(list: List<CusNews>)

        suspend fun getCoins(): Flow<List<FCoinData>>
        suspend fun insertCoins(list: List<FCoinData>)
    }

    interface RemoteRepo {
        suspend fun getNews(): List<CusNews>
        suspend fun getCoins(): List<FCoinData>
        suspend fun getChart()
    }

    interface CenterRepo {
        suspend fun getNews(): LiveData<List<CusNews>>
        suspend fun getCoins(): LiveData<List<FCoinData>>
        suspend fun refreshAll()

        suspend fun updateCoins()
        suspend fun updateNews()
        suspend fun findFullCoinDataByName(id: Long): FCoinData
        suspend fun findAboutByName(id: Long): CoinAboutData
    }
}