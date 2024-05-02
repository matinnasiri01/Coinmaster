package me.nasiri.coinmaster.util

import android.content.Context
import android.widget.Toast
import me.nasiri.coinmaster.R

fun String?.setPercent(): String = "$this%"

fun Context.showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()


fun setColor(input: Double): Int = when {
    input > 0 -> R.color.colorGain
    input < 0 -> R.color.colorLoss
    else -> R.color.secondaryTextColor
}
