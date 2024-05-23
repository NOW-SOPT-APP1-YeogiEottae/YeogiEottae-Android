package com.sopt.yeogieottae.ui.compare

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.ItemCompareRoomBinding
import com.sopt.yeogieottae.network.response.ResponseCompareRoom
import kotlinx.coroutines.CoroutineScope

class OuterViewHolder(
    private val binding: ItemCompareRoomBinding,
    private val onRoomSelected: (ResponseCompareRoom) -> Unit,
    private val deleteCompareRoom: suspend (Int) -> Unit,
    private val coroutineScope: CoroutineScope,
) : RecyclerView.ViewHolder(binding.root) {

    private var isSelected = false

    fun bind(item: ResponseCompareRoom, isEditMode: Boolean) {
        setupView(item)
        setupInnerAdapter(item, isEditMode)
        setupClickListeners(item, isEditMode)
        toggleEditModeViews(isEditMode)
    }

    private fun setupView(item: ResponseCompareRoom) {
        binding.apply {
            tvRoomName.text = item.roomName
            tvHotelName.text = item.hotelName
            Glide.with(ivRoom.context)
                .load(item.imageUrl)
                .into(ivRoom)
        }
    }

    private fun setupInnerAdapter(item: ResponseCompareRoom, isEditMode: Boolean) {
        val innerAdapter = InnerAdapter { _ ->
            if (!isEditMode) {
                toggleSelection()
            }
        }
        binding.rvCompareDetail.adapter = innerAdapter
        innerAdapter.submitList(listOf(item))
    }

    private fun setupClickListeners(item: ResponseCompareRoom, isEditMode: Boolean) {
        binding.apply {
            ivCheckbox.setOnClickListener {
                if (!isEditMode) {
                    toggleSelection()
                }
            }

            ivDelete.setOnClickListener {
                showDeleteDialog(item.roomId)
            }

            root.setOnClickListener {
                if (!isEditMode) {
                    onRoomSelected(item)
                }
            }
        }
    }

    private fun toggleEditModeViews(isEditMode: Boolean) {
        binding.apply {
            ivCheckbox.visibility = if (isEditMode) View.INVISIBLE else View.VISIBLE
            ivDelete.visibility = if (isEditMode) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun toggleSelection() {
        isSelected = !isSelected
        updateSelection(isSelected)
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

    private fun showDeleteDialog(roomId: Int) {
        val dialogFragment = DeleteDialogFragment(deleteCompareRoom, roomId, coroutineScope)
        dialogFragment.show(
            (binding.root.context as AppCompatActivity).supportFragmentManager,
            "CustomDeleteDialog"
        )
    }
}