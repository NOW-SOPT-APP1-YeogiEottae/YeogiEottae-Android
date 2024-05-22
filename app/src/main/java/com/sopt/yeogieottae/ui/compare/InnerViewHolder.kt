package com.sopt.yeogieottae.ui.compare

import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.databinding.ItemCompareDetailBinding

class InnerViewHolder(private val binding: ItemCompareDetailBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: InnerItem) {
        binding.tvMoney.text = item.money
        binding.tvStar.text = item.starRate
        binding.tvReviewCount.text = item.reviewCount
    }
}
