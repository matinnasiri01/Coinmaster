package me.nasiri.coinmaster.di


import android.widget.ImageView


interface Services {

    interface ImageLoader {
        fun loader(url: String, view: ImageView)
    }
    interface NetworkConnectionStatus {
        fun inInternetConnect(): Boolean
    }
}