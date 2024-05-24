package com.sopt.yeogieottae.ui.compare

import android.os.Bundle
import android.view.View
import com.sopt.yeogieottae.databinding.FragmentCompareNotEmptyBinding
import com.sopt.yeogieottae.ui.compare.empty.BottomSheetFragment
import com.sopt.yeogieottae.util.BaseFragment

class CompareNotEmptyFragment : BaseFragment<FragmentCompareNotEmptyBinding>(
    FragmentCompareNotEmptyBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCompareAddButton()
    }

    private fun initCompareAddButton() {
        binding.btnAdd.setOnClickListener {
            val bottomSheet = BottomSheetFragment()
            bottomSheet.show(childFragmentManager, "BottomSheetFragment")
        }
    }
}