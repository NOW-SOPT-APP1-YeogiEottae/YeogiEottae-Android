package com.sopt.yeogieottae.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.data.hotel.Hotel
import com.sopt.yeogieottae.databinding.ItemHotelListBinding

class HotelListAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    private var _binding: ItemHotelListBinding? = null
    private val binding get() = _binding!!
    private var hotels = ArrayList<Hotel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Hotel>) {
        this.hotels.clear()
        this.hotels.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        _binding = ItemHotelListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val hotel = hotels[position]
        holder.bind(hotel)
    }

    override fun getItemCount(): Int {
        return hotels.size
    }
}
