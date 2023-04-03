package com.lttrung.dormitory.database.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class CurrentUser(@field:PrimaryKey val studentId: String, val role: String, val token: String) :
    Serializable