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
        val hotelName: String,
        @SerialName("star")
        val star: String,
        @SerialName("location")
        val location: String,
        @SerialName("reviewRate")
        val reviewRate: Float,
        @SerialName("reviewCount")
        val reviewCount: Int,
        @SerialName("isLiked")
        val isLiked: Boolean,
        @SerialName("roomList")
        val roomList: List<Room>
    )
    {
        @Serializable
        data class Room(
            @SerialName("roomId")
            val roomId: Int,
            @SerialName("roomName")
            val roomName: String,
            @SerialName("price")
            val price: Int,
            @SerialName("startTime")
            val startTime: String,
            @SerialName("endTime")
            val endTime: String,
            @SerialName("imageUrl")
            val imageUrl: String,
            @SerialName("isLiked")
            val isLiked: Boolean
        )
    }
}