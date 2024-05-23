package com.sopt.yeogieottae.ui.compare

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
import com.google.android.material.snackbar.Snackbar
import com.sopt.yeogieottae.databinding.FragmentBottomSheetBinding
import com.sopt.yeogieottae.network.ServicePool
import com.sopt.yeogieottae.network.request.RequestRoomId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class BottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetBinding? = null
    private val binding: FragmentBottomSheetBinding
        get() = requireNotNull(_binding) { "FragmentBottomSheetBinding is not initialized" }

    private val bottomSheetViewModel: BottomSheetViewModel by viewModels()
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
            bottomSheetViewModel.updateSelectedCount(isSelected)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        bottomSheetViewModel.count.observe(viewLifecycleOwner) { count ->
            binding.tvCount.text = count.toString()
        }

        bottomSheetViewModel.roomList.observe(viewLifecycleOwner) { compareLikesRooms ->
            adapter.submitList(compareLikesRooms)
        }

        bottomSheetViewModel.message.observe(viewLifecycleOwner) { message ->
            Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun initAddButton() {
        binding.ivBgAddBtn.setOnClickListener {
            if (selectedRoomIds.isNotEmpty()) {
                val roomIdRequest = RequestRoomId(roomId = selectedRoomIds.toList())
                CoroutineScope(Dispatchers.Main).launch {
                    postRoomIds(roomIdRequest)
                }
            }
            dismiss()
        }
    }

    private suspend fun postRoomIds(roomIdRequest: RequestRoomId) {
        try {
            val response = ServicePool.YeogieottaeService.postCompare(roomIdRequest)

            if (response.isSuccessful) {
                compareViewModel.fetchCompareData()
            } else {
                val rawJson = response.errorBody()?.string() ?: "No error message provided"
                val error = JSONObject(rawJson).optString("message", "error message")
                Snackbar.make(binding.root, error, Snackbar.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Snackbar.make(binding.root, "네트워크 전송 오류 발생.", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.recyclerView?.adapter = null
        _binding = null
    }
}