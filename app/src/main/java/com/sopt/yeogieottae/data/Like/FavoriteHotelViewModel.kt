package com.sopt.yeogieottae.data.Like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.yeogieottae.R

class FavoriteHotelViewModel : ViewModel() {
    private val _hotels = MutableLiveData<List<FavoriteHotel>>()
    val hotels: LiveData<List<FavoriteHotel>> get() = _hotels

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        val dummyHotels = listOf(
            FavoriteHotel("그랜드 인터컨티넨탈 파르나스", 4.5F, "스탠다드 트윈룸", R.drawable.img_favorite_room, 90000),
            FavoriteHotel("인터컨티넨탈 파르나스", 4.5F, "스탠다드 트윈룸", R.drawable.img_favorite_room, 90000),
            FavoriteHotel("그랜드", 4.5F, "스탠다드 트윈룸", R.drawable.img_favorite_room, 90000),
            FavoriteHotel("그랜드파르나스", 4.5F, "스탠다드 트윈룸", R.drawable.img_favorite_room, 90000),
        )
        _hotels.value = dummyHotels
    }
}