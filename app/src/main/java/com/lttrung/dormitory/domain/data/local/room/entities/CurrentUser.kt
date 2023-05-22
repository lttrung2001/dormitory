package com.lttrung.dormitory.domain.data.local.room.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class CurrentUser(
    @PrimaryKey val username: String,
    var password: String, val role: List<String>,
    val token: String,
    @Embedded var profile: CurrentUserProfile? = null
) :
    Serializable