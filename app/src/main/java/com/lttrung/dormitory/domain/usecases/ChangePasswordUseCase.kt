package com.lttrung.dormitory.domain.usecases

import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ChangePasswordUseCase {
    fun execute(oldPassword: String, newPassword: String): Single<Unit>
}