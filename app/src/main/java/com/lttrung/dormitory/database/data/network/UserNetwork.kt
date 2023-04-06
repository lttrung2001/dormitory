package com.lttrung.dormitory.database.data.network

import com.lttrung.dormitory.database.data.network.models.UserProfileResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import javax.inject.Singleton

@Singleton
interface UserNetwork {
    fun fetchProfile(): Single<UserProfileResponse>
    fun changePassword(): Single<Unit>
}