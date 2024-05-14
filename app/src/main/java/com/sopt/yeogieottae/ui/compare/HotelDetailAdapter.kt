package com.sopt.yeogieottae.ui.compare// HotelDetailAdapter.kt
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.data.Hotel
import com.sopt.yeogieottae.databinding.ItemCompareDetailBinding

class HotelDetailAdapter(private var details: List<Hotel>) :
    RecyclerView.Adapter<HotelDetailAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = ItemCompareDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(details[position])
    }

    override fun getItemCount(): Int = details.size

    fun updateDetails(newDetails: List<Hotel>) {
        details = newDetails
        notifyDataSetChanged()
    }

    class DetailViewHolder(private val binding: ItemCompareDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hotel: Hotel) {
            binding.tvCoin.text = hotel.price
            binding.tvStar.text = "별점 ${hotel.rating}"
            binding.tvFacilities.text = hotel.facilities
        }
    }
}
