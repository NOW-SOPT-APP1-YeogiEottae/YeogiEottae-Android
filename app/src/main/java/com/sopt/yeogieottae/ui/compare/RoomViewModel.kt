package com.sopt.yeogieottae.ui.compare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.yeogieottae.data.Room

class RoomViewModel : ViewModel() {
    private val _hotels = MutableLiveData<List<Room>>()
    val hotels: LiveData<List<Room>> get() = _hotels

}
