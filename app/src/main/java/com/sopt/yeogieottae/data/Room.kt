package com.sopt.yeogieottae.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<Room>,
)

@Serializable
data class Room(
    @SerialName("room_id")
    val roomId: Int,
    @SerialName("hotel_name")
    val hotelName: String,
    @SerialName("room_type")
    val roomType: String,
    @SerialName("price")
    val price: Int,
    @SerialName("review_rate")
    val reviewRate: Double,
    @SerialName("review_count")
    val reviewCount: Int,
    @SerialName("image_url")
    val imageUrl: String,
)