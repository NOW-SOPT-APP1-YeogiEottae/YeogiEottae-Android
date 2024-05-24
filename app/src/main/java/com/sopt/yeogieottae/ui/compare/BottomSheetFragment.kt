package com.sopt.yeogieottae.ui.compare

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.sopt.yeogieottae.databinding.FragmentBottomSheetBinding
import com.sopt.yeogieottae.network.request.RequestRoomId

class BottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetBinding? = null
    private val binding: FragmentBottomSheetBinding
        get() = requireNotNull(_binding) { "FragmentBottomSheetBinding is not initialized" }

    private lateinit var compareViewModel: CompareViewModel
    private lateinit var adapter: LikeListAdapter

    private val selectedRoomIds = mutableSetOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCompareViewModel()
        setupAdapter()
        observeViewModel()
        initAddButton()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialogInterface ->
            setHeight(dialogInterface as BottomSheetDialog)
        }
        return dialog
    }

    private fun setHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.let {
            val behavior = BottomSheetBehavior.from(it)
            val layoutParams = it.layoutParams
            layoutParams.height = (resources.displayMetrics.heightPixels * 0.8).toInt()
            it.layoutParams = layoutParams
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun initCompareViewModel() {
        compareViewModel = ViewModelProvider(requireActivity())[CompareViewModel::class.java]
    }

    private fun setupAdapter() {
        adapter = LikeListAdapter { roomId, isSelected ->
            if (isSelected) {
                selectedRoomIds.add(roomId)
            } else {
                selectedRoomIds.remove(roomId)
            }
            compareViewModel.updateSelectedCount(isSelected)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        compareViewModel.count.observe(viewLifecycleOwner) { count ->
            binding.tvCount.text = count.toString()
        }

        compareViewModel.roomList.observe(viewLifecycleOwner) { compareLikesRooms ->
            adapter.submitList(compareLikesRooms)
        }

        compareViewModel.message.observe(viewLifecycleOwner) { message ->
            Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun initAddButton() {
        binding.ivBgAddBtn.setOnClickListener {
            if (selectedRoomIds.isNotEmpty()) {
                val roomIdRequest = RequestRoomId(roomId = selectedRoomIds.toList())
                compareViewModel.postRoomIds(roomIdRequest)
                parentFragmentManager.setFragmentResult("requestRoom", Bundle())
                dismiss()
            } else {
                Snackbar.make(binding.root, "선택된 방이 없습니다.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        compareViewModel.resetCount()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.recyclerView?.adapter = null
        _binding = null
    }
}