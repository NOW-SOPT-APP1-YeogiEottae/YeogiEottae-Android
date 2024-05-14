package com.sopt.yeogieottae.data

import kotlinx.serialization.SerialName

data class Room(
    @SerialName("room_id")
    val roomId: Int,
    @SerialName("hotel_name")
    val hotelName: String,
    @SerialName("room_type")
    val roomType: String,
    @SerialName("price")
    val price: Float,
    @SerialName("review_rate")
    val reviewRate: Int,
    @SerialName("review_count")
    val reviewCount: String,
    @SerialName("image_url")
    val imageUrl: String,
)