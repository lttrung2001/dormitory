package com.lttrung.dormitory.domain.usecases

import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ForgotPasswordUseCase {
    fun execute(username: String): Single<Unit>
}