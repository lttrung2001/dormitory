package com.lttrung.dormitory.domain.usecases

import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface VerifyCodeUseCase {
    fun execute(username: String, password: String, otp: String): Single<Unit>
}