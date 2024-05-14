package com.sopt.yeogieottae.ui.compare

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sopt.yeogieottae.databinding.FragmentCompareBinding
import com.sopt.yeogieottae.util.BaseFragment

class CompareFragment : BaseFragment<FragmentCompareBinding>(
    FragmentCompareBinding::inflate
) {
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RoomAdapter()
        binding.rvCompare.adapter = adapter

        roomViewModel.hotels.observe(viewLifecycleOwner) { hotels ->
            hotels?.let {
                adapter.submitList(it)
            }
        }
    }
}