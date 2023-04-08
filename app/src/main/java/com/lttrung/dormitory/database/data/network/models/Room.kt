package com.lttrung.dormitory.database.data.network.models

import com.google.gson.annotations.SerializedName

data class Room(
    @SerializedName("id") val id: Int,
    @SerializedName("giaPhong") val price: Double,
    @SerializedName("soGiuongTrong") val availableBeds: Int,
    @SerializedName("image") val image: String
) : java.io.Serializable
