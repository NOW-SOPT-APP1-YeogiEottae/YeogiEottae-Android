package com.sopt.yeogieottae.data.model

data class Room(
    val room_id: Int,
    val room_name: String,
    val price: Int,
    val start_time: String,
    val end_time: String,
    val image_url: String,
    var is_liked: Boolean,
)