package me.nasiri.coinmaster.presentation.coin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.nasiri.coinmaster.databinding.ActivityCoinBinding

class CoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}