package me.nasiri.coinmaster.ui.market

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.nasiri.coinmaster.databinding.ActivityMarketBinding
import me.nasiri.coinmaster.domain.model.lst
import me.nasiri.coinmaster.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarketBinding
    val view by viewModel<MarketViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}