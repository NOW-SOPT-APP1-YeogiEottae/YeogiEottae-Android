package com.sopt.yeogieottae.ui.compare

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sopt.yeogieottae.databinding.FragmentCompareNotEmptyBinding
import com.sopt.yeogieottae.ui.compare.empty.BottomSheetFragment
import com.sopt.yeogieottae.util.BaseFragment
import kotlinx.coroutines.launch

class CompareNotEmptyFragment : BaseFragment<FragmentCompareNotEmptyBinding>(
    FragmentCompareNotEmptyBinding::inflate
) {
    private lateinit var compareViewModel: CompareViewModel
    private lateinit var outerAdapter: OuterAdapter

    private var isEditMode = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCompareViewModel()
        setupRecyclerView()
        observeViewModel()

        binding.ivPlusBtn.setOnClickListener {
            BottomSheetFragment().show(parentFragmentManager, "BottomSheetFragment")
        }

        binding.tvBtnEdit.setOnClickListener {
            toggleEditMode()
        }

        binding.tvBtnEditDone.setOnClickListener {
            toggleEditMode()
        }
    }

    private fun initCompareViewModel() {
        compareViewModel = ViewModelProvider(requireActivity())[CompareViewModel::class.java]
    }

    private fun setupRecyclerView() {
        outerAdapter = OuterAdapter(
            onRoomSelected = { room ->
                ///todo 선택된 방을 처리 하는 로직 추가
            },
            deleteCompareRoom = { roomId ->
                lifecycleScope.launch {
                    compareViewModel.deleteRoom(roomId)
                }
            },
            coroutineScope = lifecycleScope
        )
        binding.rvCompare.adapter = outerAdapter
    }

    private fun observeViewModel() {
        compareViewModel.compareResponse.observe(viewLifecycleOwner) { response ->
            response?.result?.let { roomList ->
                outerAdapter.submitList(roomList)
            }
        }

        compareViewModel.message.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun toggleEditMode() {
        isEditMode = !isEditMode
        binding.tvBtnEdit.visibility = if (isEditMode) View.INVISIBLE else View.VISIBLE
        binding.tvBtnEditDone.visibility = if (isEditMode) View.VISIBLE else View.INVISIBLE
        outerAdapter.setEditMode(isEditMode)
    }
}