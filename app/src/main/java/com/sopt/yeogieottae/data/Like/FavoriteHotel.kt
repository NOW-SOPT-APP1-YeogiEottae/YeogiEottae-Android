package com.sopt.yeogieottae.data.Like

//@Serializable
//data class FavoriteHotel(
//    val hotelName: String,
//    val reviewRate: Float,
//    val roomInformation: List<FavoriteHotelList>,
//)
//
//data class FavoriteHotelList(
//    @SerialName("roomId")
//    val roomId: Int,
//    @SerialName("roomName")
//    val roomName: String,
//    @SerialName("roomImage")
//    val roomImage: String,
//    @SerialName("price")
//    val price: Int,
//)

data class FavoriteHotel(
    val hotelName: String,
    val reviewRate: Float,
    val roomName: String,
    val roomImage: Int,
    val price: Int,
)