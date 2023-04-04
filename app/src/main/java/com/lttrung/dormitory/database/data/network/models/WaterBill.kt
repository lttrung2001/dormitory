package com.lttrung.dormitory.database.data.network.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WaterBill(
    @SerializedName("id") val id: Int,
    @SerializedName("maSoKTX") val roomId: Int,
    @SerializedName("luongNuocTieuThu") val waterUsage: Int,
    @SerializedName("trangThai") val status: Boolean,
    @SerializedName("giaNuocTheoThang") val waterCostByMonth: WaterCostByMonth,
    @SerializedName("total") val totalCost: Double
) : Serializable
