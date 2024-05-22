package com.sopt.yeogieottae.ui.compare

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.yeogieottae.databinding.ItemCompareRoomBinding

class OuterViewHolder(private val binding: ItemCompareRoomBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: OuterItem) {
        binding.tvRoomName.text = item.roomName
        binding.tvHotelName.text = item.hotelName

        Glide.with(binding.ivRoom.context)
            .load(item.imageUrl)
            .into(binding.ivRoom)

        val innerAdapter = InnerAdapter(listOf(item.details))
        binding.rvCompareDetail.apply {
            adapter = innerAdapter
        }
    }
}
