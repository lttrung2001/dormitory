package com.lttrung.dormitory.database.data.network.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WaterCostByMonth(
    @SerializedName("id") val id: Int,
    @SerializedName("thang") val month: Int,
    @SerializedName("nam") val year: Int,
    @SerializedName("giaNuoc") val cost: Double
) : Serializable
