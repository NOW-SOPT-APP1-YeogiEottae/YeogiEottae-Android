package com.sopt.yeogieottae.ui.compare

import android.graphics.Paint
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.ItemCompareDetailBinding
import com.sopt.yeogieottae.network.response.ResponseCompareRoom
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class InnerViewHolder(
    private val binding: ItemCompareDetailBinding,
    private val onItemClicked: (ResponseCompareRoom) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ResponseCompareRoom) {
        with(binding) {
            val priceFormat = NumberFormat.getNumberInstance(Locale.getDefault())
            val reviewCountWithoutDecimal = DecimalFormat("#").format(item.reviewCount)

            tvMoneyBasic.paintFlags = (tvMoneyBasic.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
            tvPrice.text = priceFormat.format(item.price) + "원"
            tvReviewRate.text = item.reviewRate.toString()
            tvReviewCount.text = "($reviewCountWithoutDecimal)"

            root.setOnClickListener {
                onItemClicked(item)
            }
        }
    }
}