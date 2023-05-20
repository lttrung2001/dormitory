package com.lttrung.dormitory.domain.data.network.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ElectricBill(
    @SerializedName("id") val id: Int,
    @SerializedName("maSoKTX") val roomId: Int,
    @SerializedName("soDienTieuThu") val waterUsage: Int,
    @SerializedName("trangThai") val status: Boolean,
    @SerializedName("giaDienTheoThang") val electricCostByMonth: ElectricCostByMonth?,
    @SerializedName("total") val totalCost: Double
) : Serializable
