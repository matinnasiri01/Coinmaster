package me.nasiri.coinmaster.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.nasiri.coinmaster.databinding.ItemMarketNormalBinding
import me.nasiri.coinmaster.databinding.ItemMarketShimmerBinding

class MarketAdapter : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    private lateinit var binding: ItemMarketNormalBinding
    private lateinit var binding2: ItemMarketShimmerBinding

    private var viewT: Boolean = false
    private var list: ArrayList</* todo Change */Any> = arrayListOf()

    inner class MarketViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(item: Any) {


        }
    }

    override fun getItemCount(): Int = if (!viewT) 10 else list.size
    override fun getItemViewType(position: Int): Int = if (viewT) 1 else 2
    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) =
        if (viewT) holder.bind(list[position]) else Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        binding = ItemMarketNormalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        binding2 =
            ItemMarketShimmerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return MarketViewHolder(if (viewType == 1) binding.root else binding2.root)
    }

}