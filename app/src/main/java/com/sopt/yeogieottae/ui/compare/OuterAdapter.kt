package com.sopt.yeogieottae.ui.compare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.yeogieottae.databinding.ItemCompareRoomBinding
import com.sopt.yeogieottae.network.response.ResponseCompareRoom
import kotlinx.coroutines.CoroutineScope

class OuterAdapter(
    private val onRoomSelected: (ResponseCompareRoom) -> Unit,
    private val deleteCompareRoom: suspend (Int) -> Unit,
    private val coroutineScope: CoroutineScope,
) : ListAdapter<ResponseCompareRoom, OuterViewHolder>(OuterDiffCallback()) {

    private var isEditMode = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OuterViewHolder {
        val binding =
            ItemCompareRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OuterViewHolder(binding, onRoomSelected, deleteCompareRoom, coroutineScope)
    }

    override fun onBindViewHolder(holder: OuterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, isEditMode)
    }

    fun setEditMode(isEditMode: Boolean) {
        this.isEditMode = isEditMode
        notifyDataSetChanged()
    }

    class OuterDiffCallback : DiffUtil.ItemCallback<ResponseCompareRoom>() {
        override fun areItemsTheSame(
            oldItem: ResponseCompareRoom,
            newItem: ResponseCompareRoom,
        ): Boolean {
            return oldItem.roomId == newItem.roomId
        }

        override fun areContentsTheSame(
            oldItem: ResponseCompareRoom,
            newItem: ResponseCompareRoom,
        ): Boolean {
            return oldItem == newItem
        }
    }
}