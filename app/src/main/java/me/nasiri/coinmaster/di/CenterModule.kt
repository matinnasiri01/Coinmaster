package me.nasiri.coinmaster.di

import me.nasiri.coinmaster.data.remote.RemoteRepoImpl
import me.nasiri.coinmaster.data.remote.api.ApiService
import me.nasiri.coinmaster.ui.coin.CoinViewModel
import me.nasiri.coinmaster.ui.market.MarketViewModel
import me.nasiri.coinmaster.util.CoilImageLoader
import me.nasiri.coinmaster.util.ConnectionTester
import me.nasiri.coinmaster.util.Constans.BASE_URL
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val CenterModule = module {

    single<Services.NetworkConnectionStatus> { ConnectionTester(get()) }
    single<Services.ImageLoader> { CoilImageLoader() }
    single<Retrofit> {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }

    single<Services.RemoteRepo> { RemoteRepoImpl(get()) }


    viewModel { MarketViewModel(get()) }
    viewModel { CoinViewModel() }

}