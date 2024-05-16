package com.sopt.yeogieottae.ui.compare.empty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.yeogieottae.data.RoomInformation

class BottomSheetViewModel : ViewModel() {

    private val _selectedCount = MutableLiveData(INITIAL)
    val selectedCount: LiveData<Int> get() = _selectedCount

    fun updateSelectedCount(isSelected: Boolean) {
        val currentCount = _selectedCount.value ?: INITIAL
        if (isSelected && currentCount < MAX) {
            _selectedCount.value = currentCount + 1
        } else if (!isSelected && currentCount > INITIAL) {
            _selectedCount.value = currentCount - 1
        }
    }

    fun getExampleItems(): List<RoomInformation> {
        return listOf(
            RoomInformation(
                roomId = 1,
                roomName = "클래식 킹",
                roomImage = "https://bit.ly/3QLU662",
                price = 100
            ),
            RoomInformation(
                roomId = 2,
                roomName = "주니어 스위트 킹",
                roomImage = "https://bit.ly/3wBltZv",
                price = 200
            ),
            RoomInformation(
                roomId = 3,
                roomName = "디럭스 트윈",
                roomImage = "https://bit.ly/4bj0Ct4",
                price = 80
            ),
            RoomInformation(
                roomId = 4,
                roomName = "디럭스 트윈",
                roomImage = "https://bit.ly/4bj0Ct4",
                price = 80
            ),
            RoomInformation(
                roomId = 5,
                roomName = "디럭스 트윈",
                roomImage = "https://bit.ly/4bj0Ct4",
                price = 80
            ),
            RoomInformation(
                roomId = 6,
                roomName = "디럭스 트윈",
                roomImage = "https://bit.ly/4bj0Ct4",
                price = 80
            ),
            RoomInformation(
                roomId = 7,
                roomName = "디럭스 트윈",
                roomImage = "https://bit.ly/4bj0Ct4",
                price = 80
            )
        )
    }

    companion object {
        private const val INITIAL = 0
        private const val MAX = 5
    }
}