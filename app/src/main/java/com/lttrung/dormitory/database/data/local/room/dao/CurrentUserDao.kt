package com.lttrung.dormitory.database.data.local.room.dao

import androidx.room.*
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import io.reactivex.rxjava3.core.Single

@Dao
interface CurrentUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentUser(currentUser: CurrentUser)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCurrentUser(currentUser: CurrentUser): Int

    @Query("DELETE FROM CurrentUser")
    fun deleteCurrentUser(): Int

    @get:Query("SELECT * FROM CurrentUser LIMIT 1")
    val currentUser: Single<CurrentUser>
}