package com.lttrung.dormitory.domain.usecases

import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface VerifyAdminUseCase {
    fun execute(username: String, password: String, otp: String): Single<Unit>
}