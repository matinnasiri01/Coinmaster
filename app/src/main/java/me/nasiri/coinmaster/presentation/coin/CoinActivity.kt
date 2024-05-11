package me.nasiri.coinmaster.presentation.coin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import me.nasiri.coinmaster.databinding.ActivityCoinBinding
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.util.Constants.BASE_URL_IMAG
import me.nasiri.coinmaster.domain.util.Constants.SHARE
import me.nasiri.coinmaster.domain.util.setColor
import me.nasiri.coinmaster.domain.util.setRes
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinBinding
    private val viewModel by viewModel<CoinViewModel>()
    private val image by inject<Services.ImageLoader>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tool = binding.toolbarMarket
        val chart = binding.layoutChart
        val statistics = binding.layoutStatistics
        val about = binding.layoutAbout

        viewModel.findCoin(intent.getLongExtra(SHARE, 0)) {

            tool.tvT.text = it.fullName
            image.loader(BASE_URL_IMAG + it.img, tool.ivT)

            chart.apply {
                txtChartPrice.text = it.price
                txtChartUpdown.setImageResource(setRes(it.change?.toDouble()!!))
                txtChange.text = it.change
                txtChange.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        setColor(it.change.toDouble())
                    )
                )
            }

            statistics.apply {
                txtOpen.text = it.open
                txtAlgorithm.text = it.algorithm
                txtTodayHi.text = it.todayHigh
                txtTotalVolume.text = it.totalVolume
                txtTodayLow.text = it.todayLow
                txtMarketCap.text = it.marketCap
                txtChange.text = it.changeToday
                txtSupply.text = it.supply
            }

            viewModel.findAbout(it.fullName!!, this) { abo ->
                about.apply {
                    txtWebsite.text = abo.coinWebsite
                    txtTwitter.text = abo.coinTwitter
                    txtRaddit.text = abo.coinReddit
                    txtGithub.text = abo.coinGithub
                    txtAboutCoin.text = abo.coinDes
                }
            }
        }
    }
}