package com.lttrung.dormitory.database.data.network.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ElectricCostByMonth(
    @SerializedName("thang") val month: Int,
    @SerializedName("nam") val year: Int,
    @SerializedName("giaDien") val cost: Double
) : Serializable
