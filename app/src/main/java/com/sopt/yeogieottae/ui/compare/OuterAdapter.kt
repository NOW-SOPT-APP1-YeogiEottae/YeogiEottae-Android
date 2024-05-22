package com.sopt.yeogieottae.ui.compare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.databinding.ItemCompareRoomBinding

data class OuterItem(
    val imageUrl: String,
    val roomName: String,
    val hotelName: String,
    val details: InnerItem
)

class OuterAdapter(private val items: List<OuterItem>) : RecyclerView.Adapter<OuterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OuterViewHolder {
        val binding = ItemCompareRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OuterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OuterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}