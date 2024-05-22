package com.sopt.yeogieottae.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hotel(
    @SerialName("hotelId")
    val hotelId: Int,
    @SerialName("hotelName")
    val hotelName: String,
    @SerialName("location")
    val location: String,
    @SerialName("type")
    val type: String,
    @SerialName("reviewRate")
    val reviewRate: Double,
    @SerialName("reviewCount")
    val reviewCount: Int,
    @SerialName("price")
    val price: Int,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("isLiked")
    var isLiked: Boolean,
)