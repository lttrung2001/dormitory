package com.lttrung.dormitory.domain.data.network.models

import com.google.gson.annotations.SerializedName

data class GenderStats(
    @SerializedName("amountGenderTrue") val maleCount: Int,
    @SerializedName("total") val totalCount: Int
) : java.io.Serializable