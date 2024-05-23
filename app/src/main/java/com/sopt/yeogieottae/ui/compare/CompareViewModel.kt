package com.sopt.yeogieottae.ui.compare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.yeogieottae.network.ServicePool
import com.sopt.yeogieottae.network.request.RequestDeleteRoomId
import com.sopt.yeogieottae.network.response.ResponseCompareDto
import kotlinx.coroutines.launch

class CompareViewModel : ViewModel() {
    private val _compareResponse = MutableLiveData<ResponseCompareDto>()
    val compareResponse: LiveData<ResponseCompareDto> get() = _compareResponse

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    fun fetchCompareData() {
        viewModelScope.launch {
            try {
                val response = ServicePool.YeogieottaeService.compare()
                if (response.isSuccessful) {
                    _compareResponse.value = response.body()
                } else {
                    _message.value = "CompareViewModel 패치 실패"
                }
            } catch (e: Exception) {
                _message.value = "CompareViewModel 패치 에러"
            }
        }
    }

    fun deleteRoom(roomId: Int) {
        viewModelScope.launch {
            try {
                val response = ServicePool.YeogieottaeService.deleteCompare(RequestDeleteRoomId(roomId))
                if (response.isSuccessful) {
                    fetchCompareData()
                } else {
                    _message.value = "CompareViewModel 삭제 실패"
                }
            } catch (e: Exception) {
                _message.value = "CompareViewModel 삭제 에러"
            }
        }
    }
}