package com.sopt.yeogieottae.ui.compare.empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sopt.yeogieottae.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetBinding? = null
    private val binding: FragmentBottomSheetBinding
        get() = requireNotNull(_binding) { "FragmentBottomSheetBinding is not initialized" }

    private val bottomSheetViewModel: BottomSheetViewModel by viewModels()
    private lateinit var adapter: LikeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeViewModel()
        initAddButton()
    }

    private fun setupAdapter() {
        adapter = LikeListAdapter { isSelected ->
            bottomSheetViewModel.updateSelectedCount(isSelected)
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(bottomSheetViewModel.getExampleItems())
    }

    private fun observeViewModel() {
        bottomSheetViewModel.selectedCount.observe(viewLifecycleOwner) { count ->
            binding.tvCount.text = count.toString()
        }
    }

    private fun initAddButton() {
        binding.ivBgAddBtn.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialogInterface ->
            setHeight(dialogInterface as BottomSheetDialog)
        }
        return dialog
    }

    private fun setHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.let {
            val behavior = BottomSheetBehavior.from(it)
            val layoutParams = it.layoutParams
            layoutParams.height = (resources.displayMetrics.heightPixels * 0.8).toInt()
            it.layoutParams = layoutParams
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.recyclerView?.adapter = null
        _binding = null
    }
}