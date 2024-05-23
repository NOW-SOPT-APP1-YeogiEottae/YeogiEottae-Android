package com.sopt.yeogieottae.ui.compare

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.ItemCompareLikeListBinding
import com.sopt.yeogieottae.network.response.CompareLikesRoom

class LikeListViewHolder(
    private val binding: ItemCompareLikeListBinding,
    private val selectedItems: MutableSet<Int>,
    private val onCheckedChanged: (Int, Boolean) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CompareLikesRoom) {
        setRoom(item)
        updateCheckbox(item.roomId)
        initItemClickListener(item.roomId)
    }

    private fun setRoom(item: CompareLikesRoom) {
        Glide.with(binding.ivRoomImage.context)
            .load(item.imageUrl)
            .into(binding.ivRoomImage)
        binding.tvHotelName.text = item.hotelName
        binding.tvRoomName.text = item.roomName
        binding.tvRoomLocation.text = item.location
    }

    private fun updateCheckbox(roomId: Int) {
        val isSelected = selectedItems.contains(roomId)
        binding.root.setBackgroundResource(
            if (isSelected) R.drawable.bg_gray400_radius_10dp else android.R.color.transparent
        )
        binding.ivCheckbox.setImageResource(
            if (isSelected) R.drawable.ic_checkbox else R.drawable.ic_uncheckbox
        )
    }

    private fun initItemClickListener(roomId: Int) {
        binding.root.setOnClickListener {
            toggleSelection(roomId)
        }
    }

    private fun toggleSelection(roomId: Int) {
        val isSelected = selectedItems.contains(roomId)
        if (isSelected) {
            selectedItems.remove(roomId)
        } else if (selectedItems.size < 5) {
            selectedItems.add(roomId)
        } else {
            //스낵비
        }
        updateCheckbox(roomId)
        onCheckedChanged(roomId, !isSelected)
    }
}