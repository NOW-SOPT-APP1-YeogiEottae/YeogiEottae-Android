package com.sopt.yeogieottae.ui.compare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.yeogieottae.network.ServicePool
import com.sopt.yeogieottae.network.request.RequestDeleteRoomId
import com.sopt.yeogieottae.network.request.RequestRoomId
import com.sopt.yeogieottae.network.response.CompareLikesRoom
import com.sopt.yeogieottae.network.response.ResponseCompareDto
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class CompareViewModel : ViewModel() {
    private val _compareResponse = MutableLiveData<ResponseCompareDto>()
    val compareResponse: LiveData<ResponseCompareDto> get() = _compareResponse

    private val _roomList = MutableLiveData<List<CompareLikesRoom>>()
    val roomList: LiveData<List<CompareLikesRoom>> get() = _roomList

    private val _count = MutableLiveData(INITIAL)
    val count: LiveData<Int> get() = _count

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    init {
        fetchCompareData()
        fetchRoomList()
    }

    fun fetchCompareData() {
        viewModelScope.launch {
            runCatching {
                ServicePool.yeogieottaeService.compare()
            }.onSuccess { response ->
                _compareResponse.value = response.body()
            }.onFailure { e ->
                if (e is HttpException) _message.value = "CompareViewModel 패치 실패"
                else _message.value = "CompareViewModel 패치 에러"
            }
        }
    }

    private fun fetchRoomList() {
        viewModelScope.launch {
            runCatching {
                ServicePool.yeogieottaeService.getCompare()
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    response.body()?.result?.roomList?.let { rooms ->
                        _roomList.value = rooms
                    }
                } else {
                    _message.value = "RoomList 패치 실패"
                }
            }.onFailure {
                _message.value = "RoomList 패치 에러"
            }
        }
    }

    fun deleteRoom(roomId: Int) {
        viewModelScope.launch {
            runCatching {
                ServicePool.yeogieottaeService.deleteCompare(RequestDeleteRoomId(roomId))
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    fetchCompareData()
                } else {
                    _message.value = "CompareViewModel 삭제 실패"
                }
            }.onFailure {
                _message.value = "CompareViewModel 삭제 에러"
            }
        }
    }

    fun postRoomIds(roomIdRequest: RequestRoomId) {
        viewModelScope.launch {
            runCatching {
                ServicePool.yeogieottaeService.postCompare(roomIdRequest)
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    fetchCompareData()
                } else {
                    val rawJson = response.errorBody()?.string() ?: "No error message provided"
                    val error = JSONObject(rawJson).optString("message", "error message")
                    _message.value = error
                }
            }.onFailure {
                _message.value = "CompareViewModel 전송 오류 발생."
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

    fun resetCount() {
        _count.value = INITIAL
    }

    companion object {
        private const val INITIAL = 0
        private const val MAX = 5
    }
}