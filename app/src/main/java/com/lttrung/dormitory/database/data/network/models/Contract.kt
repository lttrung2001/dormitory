package com.lttrung.dormitory.database.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Contract (
    @SerializedName("id") val id: Int,
    @SerializedName("idPhongKTX") val roomId: Int,
    @SerializedName("ngayLamDon") val applyDate: Date,
    @SerializedName("tongTien") val totalCost: Double,
    @SerializedName("trangThai") val status: Boolean,
    @SerializedName("idTerm") val idTerm: Int,
    @SerializedName("term") val term: Term,
    @SerializedName("mssv") val studentId: String
        ) : java.io.Serializable