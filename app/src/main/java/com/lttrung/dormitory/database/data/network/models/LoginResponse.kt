package com.lttrung.dormitory.database.data.network.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("mssv")
    val studentId: String, @SerializedName("token")
    val token: String, @SerializedName("role")
    val role: String
) : Serializable