package com.lttrung.dormitory.ui.forgotpassword

import com.lttrung.dormitory.database.repositories.LoginRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ForgotPasswordUseCaseImpl @Inject constructor(
    private val loginRepositories: LoginRepositories
) : ForgotPasswordUseCase {
    override fun forgotPassword(username: String): Single<String> {
        return loginRepositories.forgotPassword(username)
    }
}