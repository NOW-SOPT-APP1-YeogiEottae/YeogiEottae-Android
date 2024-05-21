package com.sopt.yeogieottae.ui.compare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.yeogieottae.network.ServicePool
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
                val response = ServicePool.authService.compare()
                if (response.isSuccessful) {
                    _compareResponse.value = response.body()
                } else {
                    _message.value = "CompareViewModel 응답 실패"
                }
            } catch (e: Exception) {
                _message.value = "CompareViewModel 에러"
            }
        }
    }
}