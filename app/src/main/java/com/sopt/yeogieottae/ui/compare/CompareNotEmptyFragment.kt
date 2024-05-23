package com.sopt.yeogieottae.ui.compare

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.github.zardozz.FixedHeaderTableLayout.FixedHeaderSubTableLayout
import com.github.zardozz.FixedHeaderTableLayout.FixedHeaderTableRow
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.FragmentCompareNotEmptyBinding
import com.sopt.yeogieottae.databinding.ItemCompareRoomBinding
import com.sopt.yeogieottae.network.response.ResponseCompareRoom
import com.sopt.yeogieottae.util.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompareNotEmptyFragment : BaseFragment<FragmentCompareNotEmptyBinding>(
    FragmentCompareNotEmptyBinding::inflate
) {
    private lateinit var compareViewModel: CompareViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCompareViewModel()
        observeViewModel()
        lifecycleScope.launch {
            setupFixedHeaderTableLayout()
        }
    }

    private fun initCompareViewModel() {
        compareViewModel = ViewModelProvider(requireActivity())[CompareViewModel::class.java]
        compareViewModel.fetchCompareData()
    }

    private fun observeViewModel() {
        compareViewModel.compareResponse.observe(viewLifecycleOwner) { response ->
            response?.result?.let { roomList ->
                lifecycleScope.launch {
                    createTables(requireContext(), roomList)
                }
            }
        }

        compareViewModel.message.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun createTables(context: Context, data: List<ResponseCompareRoom>) = withContext(Dispatchers.Main) {
        val mainTable = createMainTable(context, data)
        val columnHeaderTable = createColumnHeaderTable(context)
        val rowHeaderTable = createRowHeaderTable(context, data)
        val cornerTable = createCornerTable(context)
        binding.fixedHeaderTableLayout.addViews(
            mainTable,
            columnHeaderTable,
            rowHeaderTable,
            cornerTable
        )
        binding.fixedHeaderTableLayout.setMinScale(0.2f)
    }

    private suspend fun setupFixedHeaderTableLayout() = withContext(Dispatchers.Main) {
        // Add your setup logic here if needed
    }

    private fun createMainTable(context: Context, data: List<ResponseCompareRoom>): FixedHeaderSubTableLayout {
        return FixedHeaderSubTableLayout(context).apply {
            val textSize = 20.0f
            data.forEach { item ->
                val tableRow = FixedHeaderTableRow(context)
                val detailList = listOf(item.price.toString(), item.reviewRate.toString(), item.reviewCount.toString())
                detailList.forEach { detail ->
                    tableRow.addView(
                        createTextView(
                            context,
                            detail,
                            textSize * 1.0f
                        )
                    )
                }
                addView(tableRow)
            }
        }
    }

    private fun createColumnHeaderTable(context: Context): FixedHeaderSubTableLayout {
        return FixedHeaderSubTableLayout(context).apply {
            val textSize = 20.0f
            val tableRow = FixedHeaderTableRow(context)
            val titleList = listOf("가격", "평점", "리뷰 수")
            titleList.forEach {
                tableRow.addView(createTextView(context, it, textSize))
            }
            addView(tableRow)
            setBackgroundResource(R.drawable.bottom_border)
        }
    }

    private fun createRowHeaderTable(context: Context, data: List<ResponseCompareRoom>): FixedHeaderSubTableLayout {
        return FixedHeaderSubTableLayout(context).apply {
            data.forEach { item ->
                val tableRow = FixedHeaderTableRow(context)
                tableRow.maxChildHeight = 500
                tableRow.minimumWidth = 400
                tableRow.addView(
                    createItemCompareRoomView(
                        context = context,
                        item = item
                    )
                )
                addView(tableRow)
            }
            setBackgroundResource(R.drawable.right_border)
        }
    }

    private fun createCornerTable(context: Context): FixedHeaderSubTableLayout {
        return FixedHeaderSubTableLayout(context).apply {
            val textSize = 20.0f
            val tableRow = FixedHeaderTableRow(context)
            tableRow.minimumWidth = 400
            tableRow.addView(createTextView(context, "호텔 및 객실", textSize))
            addView(tableRow)
            setBackgroundResource(R.drawable.corner_border)
        }
    }

    private fun createTextView(context: Context, text: String, textSize: Float): TextView {
        return TextView(context).apply {
            gravity = Gravity.CENTER
            this.text = text
            setBackgroundResource(R.drawable.list_border)
            setPadding(5, 5, 5, 5)
            setTextSize(textSize)
            setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }

    private fun createItemCompareRoomView(
        context: Context,
        item: ResponseCompareRoom
    ): View {
        val inflater = LayoutInflater.from(context)
        val compareBinding = ItemCompareRoomBinding.inflate(inflater, null, false).apply {
            tvRoomName.text = item.roomName
            tvHotelName.text = item.hotelName

            Glide.with(ivRoom.context)
                .load(item.imageUrl)
                .into(ivRoom)

            val innerItems = listOf(
                ResponseCompareRoom(
                    roomId = item.roomId,
                    hotelName = item.hotelName,
                    roomName = item.roomName,
                    price = item.price,
                    reviewRate = item.reviewRate,
                    reviewCount = item.reviewCount,
                    imageUrl = item.imageUrl
                )
            )
            val innerAdapter = InnerAdapter { innerItem ->
                // Implement click listener logic for inner items here
            }
            rvCompareDetail.adapter = innerAdapter
            innerAdapter.submitList(innerItems)
        }
        return compareBinding.root
    }
}