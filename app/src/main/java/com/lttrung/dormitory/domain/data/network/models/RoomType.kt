package com.lttrung.dormitory.domain.data.network.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RoomType (
    @SerializedName("id") val id: Int,
    @SerializedName("tenLoai") val name: String,
    @SerializedName("soGiuong") val numOfBeds: Int,
    @SerializedName("giaPhong") val cost: Double,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("gioiTnh") val isMale: Boolean
    ) : Serializable