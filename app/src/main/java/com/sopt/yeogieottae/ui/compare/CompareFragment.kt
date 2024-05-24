package com.sopt.yeogieottae.ui.compare

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.FragmentCompareBinding
import com.sopt.yeogieottae.ui.compare.empty.CompareEmptyFragment
import com.sopt.yeogieottae.util.BaseFragment

class CompareFragment : BaseFragment<FragmentCompareBinding>(
    FragmentCompareBinding::inflate
) {
   // private lateinit var compareViewModel: CompareViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     //   initCompareViewModel()
     //   observeCompareViewModel()
    }

   /* private fun initCompareViewModel() {
        compareViewModel = ViewModelProvider(requireActivity())[CompareViewModel::class.java]
    }

    private fun observeCompareViewModel() {
        compareViewModel.apiResponse.observe(viewLifecycleOwner) { apiResponse ->
            if (apiResponse.data.isEmpty()) {
                parentFragmentManager.commit {
                    replace(R.id.compare_fcv, CompareEmptyFragment())
                }
            } else {
                parentFragmentManager.commit {
                    replace(R.id.compare_fcv, CompareNotEmptyFragment())
                }
            }
        }
    }*/
}