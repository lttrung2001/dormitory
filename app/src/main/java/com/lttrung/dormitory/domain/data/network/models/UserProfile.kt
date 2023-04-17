package com.lttrung.dormitory.domain.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserProfile (
        @SerializedName("username") val username: String,
        @SerializedName("hoTen") val fullName: String,
        @SerializedName("gioiTinh") val isMale: Boolean,
        @SerializedName("ngaySinh") val dob: Date,
        @SerializedName("mail") val email: String,
        @SerializedName("cmnd") val identityCardId: String,
        @SerializedName("sdt") val phoneNumber: String
        ) : java.io.Serializable