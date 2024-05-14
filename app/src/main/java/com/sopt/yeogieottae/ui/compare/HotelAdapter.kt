package com.sopt.yeogieottae.ui.compare
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.Hotel
import com.sopt.yeogieottae.databinding.ItemCompareHotelsBinding

class HotelAdapter(private val hotels: List<Hotel>) :
    RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val binding = ItemCompareHotelsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HotelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.bind(hotels[position])
    }

    override fun getItemCount(): Int = hotels.size

    class HotelViewHolder(private val binding: ItemCompareHotelsBinding) : RecyclerView.ViewHolder(binding.root) {
        private val detailAdapter = HotelDetailAdapter(listOf())

        init {
            binding.rvCompareDetail.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.rvCompareDetail.adapter = detailAdapter
        }

        fun bind(hotel: Hotel) {
            binding.ivHotel.setImageResource(R.drawable.ic_launcher_background)
            detailAdapter.updateDetails(listOf(hotel))
        }
    }
}
