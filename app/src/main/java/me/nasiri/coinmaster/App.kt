package me.nasiri.coinmaster

import android.app.Application
import me.nasiri.coinmaster.di.CenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(CenterModule)
        }
    }
}