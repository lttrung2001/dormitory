package com.lttrung.dormitory.database.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class FetchRoomContractResponse(
    @SerializedName("hopDongKTX") val contract: Contract,
    @SerializedName("loaiKTX") val roomType: RoomType,
    @SerializedName("student") val userProfile: UserProfile,
    @SerializedName("datePayment") val datePayment: Date,
    @SerializedName("dateFrom") val dateFrom: Date,
    @SerializedName("dateEnd") val dateEnd: Date,
    @SerializedName("total") val totalCost: Double
): java.io.Serializable
