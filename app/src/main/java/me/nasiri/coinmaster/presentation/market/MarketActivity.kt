package me.nasiri.coinmaster.presentation.market


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.nasiri.coinmaster.databinding.ActivityMarketBinding
import me.nasiri.coinmaster.domain.util.Constants.REFKEY
import me.nasiri.coinmaster.domain.util.lunch
import me.nasiri.coinmaster.domain.util.setAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random


class MarketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarketBinding
    private val viewModel by viewModel<MarketViewModel>()

    /*todo get this adapter by DI!*/
    private val adapter = MarketAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* todo make as service and launch when local is empty */
        binding.swiper.setOnRefreshListener {
            viewModel.refresh()
            binding.swiper.isRefreshing = false

        }

        // News ~> trash Code
        val text = binding.newsMarket.txtNews
        val image = binding.newsMarket.imgNews
        viewModel.news {
            val pos = Random.nextInt(it.size - 1)
            val (_, title, url) = it[pos]
            text.text = title
            image.lunch(url)
        }


        // Coins ~> trash Code
        val recycler = binding.resMarket.recyclerItemMarket
        val buttonMore = binding.resMarket.btnMore
        recycler.setAdapter { adapter }
        buttonMore.lunch(REFKEY)
        viewModel.coins { adapter.submitList(it) }
    }
}