package com.sopt.yeogieottae.ui.room

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.FragmentRoomBinding
import com.sopt.yeogieottae.util.BaseFragment
import com.sopt.yeogieottae.util.bulletSpanText
import com.sopt.yeogieottae.util.bulletSpanTextCancel


class RoomFragment : BaseFragment<FragmentRoomBinding>(
    FragmentRoomBinding::inflate
) {
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            tvRoomDetailRoomName.text = roomViewModel.Room.value?.room_name
            tvRoomSummaryDiscountPrice.text =
                getString(R.string.all_price).format(roomViewModel.Room.value?.price)
            tvRoomDetailBottomPrice.text =
                getString(R.string.all_price).format(roomViewModel.Room.value?.price)
            tvRoomSummaryTime.text = getString(R.string.room_in_out).format(
                roomViewModel.Room.value?.start_time,
                roomViewModel.Room.value?.end_time
            )
            tvRoomDetailNormalInfoDescription.text =
                bulletSpanText(
                    requireContext(),
                    roomViewModel.detail.value?.information ?: emptyList()
                )
            tvRoomDetailFacilitiesDescription.text =
                bulletSpanText(
                    requireContext(),
                    roomViewModel.detail.value?.facilities ?: emptyList()
                )
            tvRoomDetailCancelDescription.text =
                bulletSpanTextCancel(
                    requireContext(),
                    roomViewModel.detail.value?.cancel ?: emptyList()
                )
        }
    }
}