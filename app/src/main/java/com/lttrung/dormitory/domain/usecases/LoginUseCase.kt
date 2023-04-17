package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.local.room.entities.CurrentUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface LoginUseCase {
    fun execute(username: String, password: String): Single<CurrentUser>
}