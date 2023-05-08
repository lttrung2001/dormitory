package com.lttrung.dormitory.domain.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Comment(
    val id: Int,
    @SerializedName("mssv") val studentId: String,
    @SerializedName("idRoom") val roomId: Int,
    val content: String,
    val timePost: Date
) : java.io.Serializable