package com.sopt.yeogieottae.data.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HotelViewModel : ViewModel() {
    private val _hotels = MutableLiveData<List<Hotel>>()
    val hotels: LiveData<List<Hotel>>
        get() = _hotels

    init {
        // 더미데이터 사용
        val dummyData = listOf(
            Hotel("Hotel A", "Location A", "4.5", "1000", false),
            Hotel("Hotel B", "Location B", "4.0", "800", false),
            Hotel("Hotel B", "Location B", "4.0", "800", false),
            Hotel("Hotel B", "Location B", "4.0", "800", false),
            Hotel("Hotel B", "Location B", "4.0", "800", false),
            Hotel("Hotel B", "Location B", "4.0", "800", false)
        )
        _hotels.value = dummyData
    }
}