package com.lttrung.dormitory.database.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lttrung.dormitory.database.data.local.room.dao.CurrentUserDao
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.utils.RoomUtils

@Database(entities = [CurrentUser::class], version = RoomUtils.CURRENT_USER_DATABASE_VERSION)
abstract class CurrentUserDatabase : RoomDatabase() {
    abstract fun currentUserDao(): CurrentUserDao
}