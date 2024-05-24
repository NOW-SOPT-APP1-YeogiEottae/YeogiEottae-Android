package com.sopt.yeogieottae.ui.compare

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
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


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun fetchCompareData() {
        viewModelScope.launch {
            runCatching {
                ServicePool.authService.compare()
            }.onSuccess { response ->
                _compareResponse.value = response.body()
            }.onFailure { e ->
                if(e is HttpException)  _message.value = "CompareViewModel 응답 실패"
                else _message.value = "CompareViewModel 에러"
            }
        }
    }
}