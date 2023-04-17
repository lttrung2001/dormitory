package com.lttrung.dormitory.domain.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Term(
    @SerializedName("id") val id: Int,
    @SerializedName("ngayMoDangKy") val registerStartDate: Date,
    @SerializedName("ngayKetThucDangKy") val registerEndDate: Date,
    @SerializedName("ngayKetThuc") val endDate: Date,
    @SerializedName("hanDongPhi") val paymentDeadline: Int
) : java.io.Serializable