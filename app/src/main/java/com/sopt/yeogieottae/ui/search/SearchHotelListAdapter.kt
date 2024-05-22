package com.sopt.yeogieottae.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.network.response.Hotel
import com.sopt.yeogieottae.databinding.ItemHotelListBinding

class SearchHotelListAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    private var hotels = ArrayList<Hotel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Hotel>) {
        this.hotels.clear()
        this.hotels.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            ItemHotelListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
