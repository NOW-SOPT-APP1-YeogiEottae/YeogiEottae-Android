package com.sopt.yeogieottae.ui.compare

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.sopt.yeogieottae.databinding.FragmentCompareNotEmptyBinding
import com.sopt.yeogieottae.network.response.ResponseCompareRoom
import com.sopt.yeogieottae.ui.compare.empty.BottomSheetFragment
import com.sopt.yeogieottae.util.BaseFragment

class CompareNotEmptyFragment : BaseFragment<FragmentCompareNotEmptyBinding>(
    FragmentCompareNotEmptyBinding::inflate
) {
    private lateinit var compareViewModel: CompareViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvAddBtn.setOnClickListener {
            BottomSheetFragment().show(parentFragmentManager, "BottomSheetFragment")
        }

        initCompareViewModel()
        observeViewModel()
        compareViewModel.fetchCompareData()
    }

    private fun initCompareViewModel() {
        compareViewModel = ViewModelProvider(requireActivity())[CompareViewModel::class.java]
    }

    private fun observeViewModel() {
        compareViewModel.compareResponse.observe(viewLifecycleOwner) { response ->
            response?.result?.let { roomList ->
                setupRecyclerView(roomList)
            }
        }

        compareViewModel.message.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView(data: List<ResponseCompareRoom>) {
        val items = data.map {
            OuterItem(
                imageUrl = it.imageUrl,
                roomName = it.roomName,
                hotelName = it.hotelName,
                details = InnerItem(
                    money = "${it.price}원",
                    starRate = "${it.reviewRate}",
                    reviewCount = "(${it.reviewCount})"
                )
            )
        }
        binding.rvCompare.adapter = OuterAdapter(items)
    }
}