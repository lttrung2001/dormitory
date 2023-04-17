package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.repositories.LoginRepositories
import com.lttrung.dormitory.domain.usecases.ForgotPasswordUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ForgotPasswordUseCaseImpl @Inject constructor(
    private val loginRepositories: LoginRepositories
) : ForgotPasswordUseCase {
    override fun execute(username: String): Single<Unit> {
        return loginRepositories.forgotPassword(username)
    }
}