package com.sopt.yeogieottae.data.like

import com.sopt.yeogieottae.network.response.RoomInformation

data class LikeHotelState(
    val isSuccess: Boolean,
    val message: String,
    val hotelId: String? = null,
    val hotelName: String? = null,
    val reviewRate: String? = null,
    val roomInformation: RoomInformation? = null,
    val roomId: String? = null,
    val roomName: String? = null,
    val roomImage: String? = null,
    val price: Int? = null,
)