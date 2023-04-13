package com.lttrung.dormitory.ui.forgotpassword

import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ForgotPasswordUseCase {
    fun forgotPassword(username: String): Single<String>
}