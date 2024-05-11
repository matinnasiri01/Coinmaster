package me.nasiri.coinmaster.di

import androidx.room.Room
import me.nasiri.coinmaster.data.local.database.CenterDatabase
import me.nasiri.coinmaster.data.remote.ApiService
import me.nasiri.coinmaster.data.repository.CenterRepoImpl
import me.nasiri.coinmaster.data.repository.LocalRepoImpl
import me.nasiri.coinmaster.data.repository.RemoteRepoImpl
import me.nasiri.coinmaster.domain.repository.CenterRepo
import me.nasiri.coinmaster.domain.repository.LocalRepo
import me.nasiri.coinmaster.domain.repository.RemoteRepo
import me.nasiri.coinmaster.presentation.coin.CoinViewModel
import me.nasiri.coinmaster.presentation.market.MarketViewModel
import me.nasiri.coinmaster.domain.util.CoilImageLoader
import me.nasiri.coinmaster.domain.util.ConnectionTester
import me.nasiri.coinmaster.domain.util.Constants
import me.nasiri.coinmaster.domain.util.Constants.BASE_URL
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val CenterModule = module {


    single<Services.NetworkConnectionStatus> { ConnectionTester(get()) }
    single<Services.ImageLoader> { CoilImageLoader() }

    single<ApiService> {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    single<CenterDatabase> {
        Room.databaseBuilder(
            androidContext(),
            CenterDatabase::class.java,
            Constants.DATABASENAME
        ).build()
    }

    single<LocalRepo> {
        LocalRepoImpl(
            get<CenterDatabase>().newsDao,
            get<CenterDatabase>().coinsDao
        )
    }

    single<RemoteRepo> { RemoteRepoImpl(get<ApiService>()) }

    single<CenterRepo> {
        CenterRepoImpl(
            get<Services.NetworkConnectionStatus>(),
            get<LocalRepo>(),
            get<RemoteRepo>()
        )
    }



    viewModel { MarketViewModel(get<CenterRepo>()) }
    viewModel { CoinViewModel(get<CenterRepo>()) }

}