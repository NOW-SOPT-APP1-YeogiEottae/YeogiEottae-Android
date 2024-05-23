package com.sopt.yeogieottae.ui.compare.empty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.yeogieottae.network.ServicePool
import com.sopt.yeogieottae.network.response.ResponseCompareLikesDto
import com.sopt.yeogieottae.network.response.CompareLikesRoom
import kotlinx.coroutines.launch
import retrofit2.Response

class BottomSheetViewModel : ViewModel() {
    private val _count = MutableLiveData(INITIAL)
    val count: LiveData<Int> get() = _count

    private val _roomList = MutableLiveData<List<CompareLikesRoom>>()
    val roomList: LiveData<List<CompareLikesRoom>> get() = _roomList

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    init {
        fetchRoomList()
    }

    private fun fetchRoomList() {
        viewModelScope.launch {
            try {
                val response: Response<ResponseCompareLikesDto> =
                    ServicePool.YeogieottaeService.getCompare()
                if (response.isSuccessful) {
                    response.body()?.result?.roomList?.let { rooms ->
                        _roomList.value = rooms
                    }
                } else {
                    _message.value = "BottomSheetViewmodel 응답 실패"
                }
            } catch (e: Exception) {
                _message.value = "BottomSheetViewmodel 오류 발생"
            }
        }
    }

    fun updateSelectedCount(isSelected: Boolean) {
        val currentCount = _count.value ?: INITIAL
        if (isSelected) {
            if (currentCount < MAX) {
                _count.value = currentCount + 1
            }
        } else {
            if (currentCount > INITIAL) {
                _count.value = currentCount - 1
            }
        }
    }

    companion object {
        private const val INITIAL = 0
        private const val MAX = 5
    }
}