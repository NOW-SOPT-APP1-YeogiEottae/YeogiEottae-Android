package com.sopt.yeogieottae.ui.like

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.data.like.LikeHotelState
import com.sopt.yeogieottae.databinding.ItemFavoriteHotelBinding

class FavoriteHotelListAdapter(
    private val onItemLongClick: (LikeHotelState) -> Unit,
) : RecyclerView.Adapter<FavoriteHotelViewHolder>() {
    private var hotels = ArrayList<LikeHotelState>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<LikeHotelState>) {
        hotels.clear()
        hotels.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHotelViewHolder {
        val binding =
            ItemFavoriteHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHotelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteHotelViewHolder, position: Int) {
        holder.bind(hotels[position])
        holder.itemView.setOnLongClickListener {
            onItemLongClick(hotels[position])
            true
        }
    }

    override fun getItemCount(): Int {
        return hotels.size
    }
}
