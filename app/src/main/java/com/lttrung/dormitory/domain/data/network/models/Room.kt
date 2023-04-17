package com.lttrung.dormitory.domain.data.network.models

import com.google.gson.annotations.SerializedName

data class Room(
    @SerializedName("id") val id: Int,
    @SerializedName("nameTypeRoom") val name: String,
    @SerializedName("cost") val price: Double,
    @SerializedName("emptyBed") val availableBeds: Int,
    @SerializedName("image") val image: String,
    @SerializedName("desc") val description: String
) : java.io.Serializable
