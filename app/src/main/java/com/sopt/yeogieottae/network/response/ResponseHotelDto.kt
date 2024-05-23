package com.sopt.yeogieottae.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseHotelDto(
    @SerialName("code")
    val code: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: Hotel,
) {
    @Serializable
    data class Hotel(
        @SerialName("hotelName")
        val hotel_name: String,
        @SerialName("star")
        val star: String,
        @SerialName("location")
        val location: String,
        @SerialName("reviewRate")
        val review_rate: Float,
        @SerialName("reviewCount")
        val review_count: Int,
        @SerialName("isLiked")
        val is_liked: Boolean,
        @SerialName("roomList")
        val room_list: List<Room>
    )
    {
        @Serializable
        data class Room(
            @SerialName("roomId")
            val room_id: Int,
            @SerialName("roomName")
            val room_name: String,
            @SerialName("price")
            val price: Int,
            @SerialName("startTime")
            val start_time: String,
            @SerialName("endTime")
            val end_time: String,
            @SerialName("imageUrl")
            val image_url: String,
            @SerialName("isLiked")
            val is_liked: Boolean
        )
    }
}