package com.lttrung.dormitory.ui.verifycode

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface VerifyCodeUseCase {
    fun verifyCode(username: String, password: String, otp: String): Single<String>
}