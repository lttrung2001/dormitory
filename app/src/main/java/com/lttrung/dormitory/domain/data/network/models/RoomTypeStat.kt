package com.lttrung.dormitory.domain.data.network.models

import com.google.gson.annotations.SerializedName

data class RoomTypeStat(
    @SerializedName("nameRoomtype") val type: String,
    @SerializedName("amoutStudents") val studentCount: Int,
    @SerializedName("price") val price: Double
)
