package com.lttrung.dormitory.database.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import com.lttrung.dormitory.database.data.local.room.dao.CurrentUserDao
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.utils.Converters
import com.lttrung.dormitory.utils.RoomUtils

@Database(entities = [CurrentUser::class], version = RoomUtils.CURRENT_USER_DATABASE_VERSION)
@TypeConverters(Converters::class)
abstract class CurrentUserDatabase : RoomDatabase() {
    abstract fun currentUserDao(): CurrentUserDao

    companion object {
        val MIGRATE_1_2 = Migration(1, 2) {
            it.execSQL("ALTER TABLE CurrentUser ADD COLUMN password TEXT DEFAULT ''")
        }
        val MIGRATE_2_3 = Migration(2, 3) {
            it.beginTransaction()
            it.execSQL("ALTER TABLE CurrentUser ADD COLUMN fullName TEXT DEFAULT ''")
            it.execSQL("ALTER TABLE CurrentUser ADD COLUMN isMale NUMBER DEFAULT 1")
            it.execSQL("ALTER TABLE CurrentUser ADD COLUMN dob NUMBER DEFAULT ''")
            it.execSQL("ALTER TABLE CurrentUser ADD COLUMN email TEXT DEFAULT ''")
            it.execSQL("ALTER TABLE CurrentUser ADD COLUMN identityCardId TEXT DEFAULT ''")
            it.execSQL("ALTER TABLE CurrentUser ADD COLUMN phoneNumber TEXT DEFAULT ''")
            it.setTransactionSuccessful()
            it.endTransaction()
        }
    }
}