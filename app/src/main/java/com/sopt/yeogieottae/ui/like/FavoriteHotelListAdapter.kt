package com.sopt.yeogieottae.ui.like

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.data.like.LikeHotelState
import com.sopt.yeogieottae.databinding.ItemFavoriteHotelBinding

class FavoriteHotelListAdapter(
    private val onItemLongClick: (LikeHotelState) -> Unit
) : RecyclerView.Adapter<FavoriteHotelViewHolder>() {

    private val hotels = mutableListOf<LikeHotelState>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<LikeHotelState>) {
        hotels.clear()
        hotels.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHotelViewHolder {
        val binding = ItemFavoriteHotelBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavoriteHotelViewHolder(binding, onItemLongClick)
    }

    override fun onBindViewHolder(holder: FavoriteHotelViewHolder, position: Int) {
        holder.bind(hotels[position])
    }

    override fun getItemCount(): Int = hotels.size
}