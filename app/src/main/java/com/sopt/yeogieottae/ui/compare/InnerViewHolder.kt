package com.sopt.yeogieottae.ui.compare

import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.databinding.ItemCompareDetailBinding
import com.sopt.yeogieottae.network.response.ResponseCompareRoom

class InnerViewHolder(
    private val binding: ItemCompareDetailBinding,
    private val onItemClicked: (ResponseCompareRoom) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ResponseCompareRoom) {
        binding.apply {
            tvPrice.text = item.price.toString()
            tvReviewRate.text = item.reviewRate.toString()
            tvReviewCount.text = item.reviewCount.toString()

            root.setOnClickListener {
                onItemClicked(item)
            }
        }
    }
}