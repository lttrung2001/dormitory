package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.network.models.UserProfile
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ViewProfileUseCase {
    fun execute(): Single<UserProfile>
    fun logout()
}