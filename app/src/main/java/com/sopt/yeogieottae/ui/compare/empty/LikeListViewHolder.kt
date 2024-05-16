package com.sopt.yeogieottae.ui.compare.empty

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.RoomInformation
import com.sopt.yeogieottae.databinding.ItemLikeListBinding

class LikeListViewHolder(
    private val binding: ItemLikeListBinding,
    private val selectedItems: MutableSet<Int>,
    private val onCheckedChanged: (Boolean) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RoomInformation, position: Int) {
        val imageSize = binding.ivRoomImage.width
        Glide.with(binding.ivRoomImage.context)
            .load(item.roomImage)
            .placeholder(R.drawable.ic_launcher_foreground)
            .override(imageSize, imageSize)
            .centerCrop()
            .into(binding.ivRoomImage)

        binding.tvRoomName.text = item.roomName
        updateCheckbox(position)

        binding.root.setOnClickListener {
            toggleSelection(position)
        }

        binding.cbSelected.setOnClickListener {
            toggleSelection(position)
        }
    }

    private fun toggleSelection(position: Int) {
        val isSelected = selectedItems.contains(position)
        if (isSelected) {
            selectedItems.remove(position)
        } else if (selectedItems.size < 5) {
            selectedItems.add(position)
        }
        updateCheckbox(position)
        onCheckedChanged(!isSelected)
    }

    private fun updateCheckbox(position: Int) {
        val isSelected = selectedItems.contains(position)
        binding.root.setBackgroundResource(if (isSelected) R.drawable.bg_gray400_radius_10dp else android.R.color.transparent)
        binding.cbSelected.setImageResource(if (isSelected) R.drawable.ic_checkbox else R.drawable.ic_uncheckbox)
    }
}