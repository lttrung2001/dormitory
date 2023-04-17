package com.lttrung.dormitory.domain.data.local.room.entities

data class CurrentUserProfile(
    val fullName: String,
    val isMale: Boolean,
    val dob: Long,
    val email: String,
    val identityCardId: String,
    val phoneNumber: String
) : java.io.Serializable