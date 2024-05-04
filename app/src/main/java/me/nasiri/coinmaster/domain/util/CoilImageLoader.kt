package me.nasiri.coinmaster.domain.util

import android.widget.ImageView
import coil.load
import me.nasiri.coinmaster.di.Services

class CoilImageLoader : Services.ImageLoader {
    override fun loader(url: String, view: ImageView) {
        view.load(url)
    }
}