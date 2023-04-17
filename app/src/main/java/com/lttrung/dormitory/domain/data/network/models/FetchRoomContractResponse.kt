package com.lttrung.dormitory.domain.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class FetchRoomContractResponse(
    @SerializedName("hopDongKTX") val contract: Contract,
    @SerializedName("nameRoomtype") val roomTypeName: String,
    @SerializedName("price") val price: Double,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("mail") val email: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("datePayment") val datePayment: Date,
    @SerializedName("dateFrom") val dateFrom: Date,
    @SerializedName("dateEnd") val dateEnd: Date,
): java.io.Serializable
