package com.sopt.yeogieottae.ui.compare

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresExtension
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.FragmentCompareBinding
import com.sopt.yeogieottae.util.BaseFragment

class CompareFragment : BaseFragment<FragmentCompareBinding>(
    FragmentCompareBinding::inflate
) {
    private lateinit var compareViewModel: CompareViewModel

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCompareViewModel()
        observeCompareViewModel()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun initCompareViewModel() {
        compareViewModel = ViewModelProvider(requireActivity())[CompareViewModel::class.java]
        compareViewModel.fetchCompareData()
    }

    private fun observeCompareViewModel() {
        compareViewModel.compareResponse.observe(viewLifecycleOwner) { compareResponse ->
            if (compareResponse.result.isEmpty()) {
                childFragmentManager.commit {
                    replace(R.id.compare_fcv, CompareEmptyFragment())
                }
            } else {
                childFragmentManager.commit {
                    replace(R.id.compare_fcv, CompareNotEmptyFragment())
                }
            }
        }

        compareViewModel.message.observe(viewLifecycleOwner) { message ->
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}