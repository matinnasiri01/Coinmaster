package me.nasiri.coinmaster.presentation.market

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.nasiri.coinmaster.databinding.ActivityMarketBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarketBinding
    private val viewModel by viewModel<MarketViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}