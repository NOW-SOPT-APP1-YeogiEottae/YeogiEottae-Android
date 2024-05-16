package com.sopt.yeogieottae.ui.like

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.FragmentLikeBinding
import com.sopt.yeogieottae.util.BaseFragment

class LikeFragment : BaseFragment<FragmentLikeBinding>(
    FragmentLikeBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCompareButton()
    }

    private fun initCompareButton() {
        binding.compareBtn.setOnClickListener {
            findNavController().navigate(R.id.action_like_to_compare)
        }
    }
}