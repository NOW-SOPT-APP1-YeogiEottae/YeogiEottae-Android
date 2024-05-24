package com.sopt.yeogieottae.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.yeogieottae.network.response.Hotel
import com.sopt.yeogieottae.databinding.ItemHotelListBinding

class SearchHotelListAdapter(
    private val searchViewModel: SearchViewModel,
    private val itemClickEvent: (Int) -> Unit
) : ListAdapter<Hotel, SearchViewHolder>(HotelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemHotelListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding, searchViewModel, itemClickEvent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val hotel = getItem(position)
        holder.bind(hotel)
    }
}

class HotelDiffCallback : DiffUtil.ItemCallback<Hotel>() {
    override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
        return oldItem.hotelId == newItem.hotelId
    }

    override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
        return oldItem == newItem
    }
}
