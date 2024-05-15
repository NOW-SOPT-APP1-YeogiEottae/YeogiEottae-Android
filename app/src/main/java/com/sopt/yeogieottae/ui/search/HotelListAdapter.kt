package com.sopt.yeogieottae.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.hotel.Hotel

class HotelListAdapter : RecyclerView.Adapter<HotelListAdapter.HotelViewHolder>() {

    private var hotels = emptyList<Hotel>()

    inner class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tv_hotel_name)
        val locationTextView: TextView = itemView.findViewById(R.id.tv_hotel_location)
        val ratingTextView: TextView = itemView.findViewById(R.id.tv_hotel_rating)
        val reviewCountTextView: TextView = itemView.findViewById(R.id.tv_hotel_review_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hotel_list, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hotels[position]
        holder.nameTextView.text = hotel.name
        holder.locationTextView.text = hotel.location
        holder.ratingTextView.text = hotel.rating
        holder.reviewCountTextView.text = hotel.reviewCount
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    fun setHotels(hotels: List<Hotel>) {
        this.hotels = hotels
        notifyDataSetChanged()
    }
}
