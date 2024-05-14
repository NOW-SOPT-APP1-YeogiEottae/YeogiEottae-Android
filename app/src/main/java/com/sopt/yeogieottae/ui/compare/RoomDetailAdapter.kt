package com.sopt.yeogieottae.ui.compare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.data.Room
import com.sopt.yeogieottae.databinding.ItemCompareDetailBinding

class RoomDetailAdapter(private var details: List<Room>) :
    RecyclerView.Adapter<RoomDetailAdapter.DetailViewHolder>() {

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

    fun updateDetails(newDetails: List<Room>) {
        details = newDetails
        notifyDataSetChanged()
    }

    class DetailViewHolder(private val binding: ItemCompareDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(room: Room) {
            binding.tvMoney.text = room.price.toString()
        }
    }
}
