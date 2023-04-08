package com.lttrung.dormitory.database.data.local.room.entities

import java.util.*

data class CurrentUserProfile(
    val fullName: String,
    val isMale: Boolean,
    val dob: Long,
    val email: String,
    val identityCardId: String,
    val phoneNumber: String
) : java.io.Serializable