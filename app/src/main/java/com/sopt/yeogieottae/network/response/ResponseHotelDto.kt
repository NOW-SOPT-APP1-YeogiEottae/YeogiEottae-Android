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
        @SerialName("hotel_name")
        val hotel_name: String,
        @SerialName("star")
        val star: String,
        @SerialName("location")
        val location: String,
        @SerialName("review_rate")
        val review_rate: Float,
        @SerialName("review_count")
        val review_count: Int,
        @SerialName("is_liked")
        val is_liked: Boolean,
        @SerialName("room_list")
        val room_list: List<Room>
    )
    {
        @Serializable
        data class Room(
            @SerialName("room_id")
            val room_id: Int,
            @SerialName("room_name")
            val room_name: String,
            @SerialName("price")
            val price: Int,
            @SerialName("start_time")
            val start_time: String,
            @SerialName("end_time")
            val end_time: String,
            @SerialName("image_url")
            val image_url: String,
            @SerialName("is_liked")
            val is_liked: Boolean
        )
    }
}


