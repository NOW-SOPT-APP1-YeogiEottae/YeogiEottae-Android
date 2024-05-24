package com.sopt.yeogieottae.ui.compare

import android.os.Bundle
import android.view.View
import com.sopt.yeogieottae.databinding.FragmentCompareEmptyBinding
import com.sopt.yeogieottae.util.BaseFragment

class CompareEmptyFragment : BaseFragment<FragmentCompareEmptyBinding>(
    FragmentCompareEmptyBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAddTextButton()
    }

    private fun initAddTextButton() {
        binding.tvAdd.setOnClickListener {
            val bottomSheet = BottomSheetFragment()
            bottomSheet.show(childFragmentManager, "BottomSheetFragment")
        }
    }
}