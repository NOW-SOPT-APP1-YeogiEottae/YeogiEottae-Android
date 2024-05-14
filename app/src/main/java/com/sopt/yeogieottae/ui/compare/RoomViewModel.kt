package com.sopt.yeogieottae.ui.compare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.yeogieottae.data.Room

class RoomViewModel : ViewModel() {
    private val _hotels = MutableLiveData<List<Room>>()
    val hotels: LiveData<List<Room>> get() = _hotels

    init {
        loadHotels()
    }

    private fun loadHotels() {
        _hotels.value = listOf(
            Room(1, "호텔이름", "디럭스", 400_000f, 4, "1004", "부대시설: 이런거 저런거 있음"),
            Room(2, "호텔이름", "디럭스", 400_000f, 3, "1004", "부대시설: 이런거 저런거 있음"),
            Room(3, "호텔이름", "디럭스", 400_000f, 2, "1004", "부대시설: 이런거 저런거 있음"),
            Room(4, "호텔이름", "디럭스", 400_000f, 1, "1004", "부대시설: 이런거 저런거 있음"),
            Room(5, "호텔이름", "디럭스", 400_000f, 5, "1004", "부대시설: 이런거 저런거 있음")
        )
    }
}
