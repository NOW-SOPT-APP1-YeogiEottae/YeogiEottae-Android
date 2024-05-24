package com.sopt.yeogieottae.data.model

data class Room(
    val roomId: Int,
    val roomName: String,
    val price: Int,
    val startTime: String,
    val endTime: String,
    val imageUrl: String,
    var isLiked: Boolean,
)