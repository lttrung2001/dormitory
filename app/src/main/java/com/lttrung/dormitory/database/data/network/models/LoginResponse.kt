package com.lttrung.dormitory.database.data.network.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("username") val studentId: String,
    @SerializedName("token") val token: String,
    @SerializedName("roles") val roles: List<String>
) : Serializable