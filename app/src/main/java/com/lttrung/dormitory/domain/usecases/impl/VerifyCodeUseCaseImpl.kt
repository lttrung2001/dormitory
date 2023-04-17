package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.repositories.LoginRepositories
import com.lttrung.dormitory.domain.usecases.VerifyCodeUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class VerifyCodeUseCaseImpl @Inject constructor(
    private val repositories: LoginRepositories
) : VerifyCodeUseCase {
    override fun execute(username: String, password: String, otp: String): Single<Unit> {
        return repositories.verifyCode(username, password, otp)
    }
}