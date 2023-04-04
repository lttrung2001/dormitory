package com.lttrung.dormitory.database.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.lttrung.dormitory.database.data.local.room.dao.CurrentUserDao
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.utils.RoomUtils

@Database(entities = [CurrentUser::class], version = RoomUtils.CURRENT_USER_DATABASE_VERSION)
abstract class CurrentUserDatabase : RoomDatabase() {
    abstract fun currentUserDao(): CurrentUserDao

    companion object {
        val MIGRATE_1_2 = Migration(1, 2) {
            it.execSQL("ALTER TABLE CurrentUser ADD COLUMN password TEXT DEFAULT ''")
        }
    }
}