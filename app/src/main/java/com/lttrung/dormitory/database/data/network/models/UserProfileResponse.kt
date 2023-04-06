package com.lttrung.dormitory.database.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserProfileResponse(
    @SerializedName("personalInfo") val userProfile: UserProfile,
    @SerializedName("idPhong") val roomId: Int,
    @SerializedName("ngayLamDon") val applicationDate: Date,
    @SerializedName("ngayBatDau") val startDate: Date,
    @SerializedName("ngayKetThuc") val endDate: Date,
    @SerializedName("trangThaiThanhToan") val status: Boolean
) : java.io.Serializable