package me.nasiri.coinmaster.presentation.market

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import me.nasiri.coinmaster.databinding.ItemMarketNormalBinding
import me.nasiri.coinmaster.databinding.ItemMarketShimmerBinding
import me.nasiri.coinmaster.di.Services
import me.nasiri.coinmaster.domain.model.SCoinData
import me.nasiri.coinmaster.domain.util.Constants.BASE_URL_IMAG
import me.nasiri.coinmaster.domain.util.setColor
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MarketAdapter : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>(), KoinComponent {
    private var items: List<SCoinData> = emptyList()
    private var isLoading: Boolean = true
    private val image by inject<Services.ImageLoader>()

    private lateinit var binding: ItemMarketNormalBinding


    inner class MarketViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(item: SCoinData) {
            binding.apply {
                image.loader(BASE_URL_IMAG + item.img!!, imgItem)
                txtCoinName.text = item.coinName
                txtChanges.text = item.change
                txtChanges.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        setColor(item.change!!.toDouble())
                    )
                )
                txtMarketCap.text = item.marketCap
                txtPrice.text = item.price
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemMarketNormalBinding.inflate(inflater, parent, false)
        val view = when (viewType) {
            VIEW_TYPE_SHIMMER -> ItemMarketShimmerBinding.inflate(inflater, parent, false)
            else -> binding
        }
        return MarketViewHolder(view.root)
    }

    override fun getItemCount(): Int = items.size


    override fun getItemViewType(position: Int): Int =
        if (isLoading) VIEW_TYPE_SHIMMER else VIEW_TYPE_NORMAL


    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<SCoinData>) {
        isLoading = false
        items = list
        notifyDataSetChanged()
    }

    companion object {
        private const val VIEW_TYPE_SHIMMER = 1
        private const val VIEW_TYPE_NORMAL = 2
    }
}