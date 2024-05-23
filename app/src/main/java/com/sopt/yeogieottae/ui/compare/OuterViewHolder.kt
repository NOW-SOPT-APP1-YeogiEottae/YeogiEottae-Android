package com.sopt.yeogieottae.ui.compare

import android.app.AlertDialog
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.ItemCompareRoomBinding
import com.sopt.yeogieottae.network.response.ResponseCompareRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class OuterViewHolder(
    private val binding: ItemCompareRoomBinding,
    private val onRoomSelected: (ResponseCompareRoom) -> Unit,
    private val deleteCompareRoom: suspend (Int) -> Unit,
    private val coroutineScope: CoroutineScope,
) : RecyclerView.ViewHolder(binding.root) {

    private var isSelected = false

    fun bind(item: ResponseCompareRoom, isEditMode: Boolean) {
        binding.apply {
            tvRoomName.text = item.roomName
            tvHotelName.text = item.hotelName

            Glide.with(ivRoom.context)
                .load(item.imageUrl)
                .into(ivRoom)

            val innerAdapter = InnerAdapter { innerItem ->
                if (!isEditMode) {
                    toggleSelection(innerItem.roomId, isEditMode)
                }
            }
            rvCompareDetail.adapter = innerAdapter
            innerAdapter.submitList(listOf(item))

            ivCheckbox.setOnClickListener {
                if (!isEditMode) {
                    toggleSelection(item.roomId, isEditMode)
                }
            }

            ivDelete.setOnClickListener {
                showDeleteDialog(item)
            }

            root.setOnClickListener {
                if (!isEditMode) {
                    onRoomSelected(item)
                }
            }

            ivCheckbox.visibility = if (isEditMode) View.INVISIBLE else View.VISIBLE
            ivDelete.visibility = if (isEditMode) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun toggleSelection(roomId: Int, isEditMode: Boolean) {
        if (!isEditMode) {
            isSelected = !isSelected
            updateSelection(isSelected)
        }
    }

    private fun updateSelection(isSelected: Boolean) {
        binding.apply {
            root.setBackgroundColor(
                if (isSelected) ContextCompat.getColor(root.context, R.color.secondary_200)
                else ContextCompat.getColor(root.context, R.color.transparent)
            )
            ivCheckbox.setImageResource(
                if (isSelected) R.drawable.ic_checkbox
                else R.drawable.ic_uncheckbox
            )
        }
    }

    private fun showDeleteDialog(item: ResponseCompareRoom) {
        AlertDialog.Builder(binding.root.context)
            .setTitle("방 삭제")
            .setMessage("진짜 삭제?")
            .setPositiveButton("ㅇㅇ") { dialog, which ->
                coroutineScope.launch {
                    deleteCompareRoom(item.roomId)
                }
            }
            .setNegativeButton("ㄴㄴ", null)
            .show()
    }
}