package com.sopt.yeogieottae.ui.like

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.data.Like.FavoriteHotel
import com.sopt.yeogieottae.databinding.ItemFavoriteHotelBinding

class FavoriteHotelListAdapter(
    private val onItemLongClick: (FavoriteHotel) -> Unit,
) : RecyclerView.Adapter<FavoriteHotelViewHolder>() {
    private var hotels = ArrayList<FavoriteHotel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<FavoriteHotel>) {
        this.hotels.clear()
        this.hotels.addAll(items)
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
