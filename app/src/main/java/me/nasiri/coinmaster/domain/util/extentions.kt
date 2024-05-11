package me.nasiri.coinmaster.domain.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.nasiri.coinmaster.R

fun String?.setPercent(): String = "$this%"

fun Context.showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun <T : RecyclerView.ViewHolder?> RecyclerView.setAdapter(adapter: () -> RecyclerView.Adapter<T>) {
    this.adapter = adapter()
    this.layoutManager = LinearLayoutManager(this.context)
}

fun setColor(input: Double): Int = when {
    input > 0 -> R.color.colorGain
    input < 0 -> R.color.colorLoss
    else -> R.color.secondaryTextColor
}


fun setRes(input: Double): Int = when {
    input > 0 -> R.drawable.ic_tre_up
    input < 0 -> R.drawable.ic_tre_dw
    else -> R.drawable.ic_tre_zr
}


fun View.lunch(url: String) {
    this.setOnClickListener {
        this.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}
