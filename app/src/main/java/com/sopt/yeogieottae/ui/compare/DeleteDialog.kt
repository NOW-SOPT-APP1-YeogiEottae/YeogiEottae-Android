package com.sopt.yeogieottae.ui.compare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.DialogDeleteBinding
import com.sopt.yeogieottae.databinding.FragmentBottomSheetBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DeleteDialogFragment(
    private val deleteCompareRoom: suspend (Int) -> Unit,
    private val roomId: Int,
    private val coroutineScope: CoroutineScope,
) : DialogFragment() {

    private var _binding: DialogDeleteBinding? = null
    private val binding: DialogDeleteBinding
        get() = requireNotNull(_binding) { "DialogDeleteBinding is not initialized" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialogSize()
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_white_radius_18dp)

        binding.btnNo.setOnClickListener {
            dismiss()
        }
        binding.btnYes.setOnClickListener {
            coroutineScope.launch {
                deleteCompareRoom(roomId)
                dismiss()
            }
        }
    }

    private fun setDialogSize() {
        val displayMetrics = resources.displayMetrics
        val widthPixels = displayMetrics.widthPixels

        val params = dialog?.window?.attributes
        params?.width = (widthPixels * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}