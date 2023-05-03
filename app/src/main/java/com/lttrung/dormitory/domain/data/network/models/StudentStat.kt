package com.lttrung.dormitory.domain.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class StudentStat(
    @SerializedName("idTerm") val term: Int,
    @SerializedName("dateStart") val startDate: Date,
    @SerializedName("dateEnd") val endDate: Date,
    @SerializedName("amountStudent") val studentCount: Int
): java.io.Serializable