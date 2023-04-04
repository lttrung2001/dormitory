package com.lttrung.dormitory.ui.verifycode

import com.lttrung.dormitory.database.repositories.LoginRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class VerifyCodeUseCaseImpl @Inject constructor(
    private val repositories: LoginRepositories
) : VerifyCodeUseCase {
    override fun verifyCode(username: String, password: String, otp: String): Single<String> {
        return repositories.verifyCode(username, password, otp)
    }
}