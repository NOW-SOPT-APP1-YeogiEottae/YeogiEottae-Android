package com.sopt.yeogieottae.ui.compare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.databinding.ItemCompareDetailBinding

data class InnerItem(
    val money: String,
    val starRate: String,
    val reviewCount: String
)

class InnerAdapter(private val items: List<InnerItem>) : RecyclerView.Adapter<InnerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val binding =
            ItemCompareDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}