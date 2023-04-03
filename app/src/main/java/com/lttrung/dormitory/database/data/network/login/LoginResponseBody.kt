package com.lttrung.dormitory.database.data.network.login

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponseBody(
    @SerializedName("mssv")
    val studentId: String, @SerializedName("token")
    val token: String, @SerializedName("role")
    val role: String
) : Serializable