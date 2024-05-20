package com.sopt.yeogieottae.data.hotel

data class Hotel(
    val name: String,
    val location: String,
    val rating: String,
    val reviewCount: String,
    var isLiked: Boolean,
)
