package com.sopt.yeogieottae.ui.room

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.text.style.TextAppearanceSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.FragmentRoomBinding
import com.sopt.yeogieottae.util.BaseFragment


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
            tvRoomSummaryDiscountPrice.text =  getString(R.string.all_price).format(roomViewModel.Room.value?.price)
            tvRoomDetailBottomPrice.text =
                getString(R.string.all_price).format(roomViewModel.Room.value?.price)
            tvRoomSummaryTime.text = getString(R.string.room_in_out).format(
                roomViewModel.Room.value?.start_time,
                roomViewModel.Room.value?.end_time
            )
            tvRoomDetailNormalInfoDescription.text =
                createSpannedText(roomViewModel.detail.value?.information ?: emptyList())
            tvRoomDetailFacilitiesDescription.text =
                createSpannedText(roomViewModel.detail.value?.facilities ?: emptyList())
            tvRoomDetailCancelDescription.text =
                createSpannedTextCancel(roomViewModel.detail.value?.cancel ?: emptyList())
        }
    }

    private fun createSpannedText(
        texts: List<String>,
        bulletColor: Int = ContextCompat.getColor(requireContext(), R.color.gray_700)
    ): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder()

        for ((index, text) in texts.withIndex()) {
            val spannableString = SpannableString(text)
            val bulletSpan = BulletSpan(3, bulletColor, 1)

            spannableString.setSpan(
                bulletSpan,
                0,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableStringBuilder.append(spannableString)
            if (index < texts.size - 1) {
                spannableStringBuilder.append("\n")
            }
        }
        return spannableStringBuilder
    }

    private fun createSpannedTextCancel(
        texts: List<String>,
        bulletColor: Int = ContextCompat.getColor(requireContext(), R.color.gray_700)
    ): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder()
        for ((index, text) in texts.withIndex()) {
            val spannableString = SpannableString(text)
            val bulletSpan = BulletSpan(3, bulletColor, 1)

            spannableString.setSpan(
                bulletSpan,
                0,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            if (index == 3) {
                spannableString.setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.cuation)),
                    0,
                    spannableString.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannableString.setSpan(
                    TextAppearanceSpan(
                        requireContext(),
                        R.style.TextAppearance_Yeogieotte_Pretendard_h5
                    ),
                    0,
                    spannableString.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            if (index in 4..5) {
                spannableString.setSpan(
                    TextAppearanceSpan(
                        requireContext(),
                        R.style.TextAppearance_Yeogieotte_Pretendard_h5
                    ),
                    0,
                    spannableString.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            spannableStringBuilder.append(spannableString)

            if (index < texts.size - 1) {
                spannableStringBuilder.append("\n")
            }
        }
        return spannableStringBuilder
    }
}