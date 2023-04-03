package com.lttrung.dormitory.ui.login

import com.lttrung.dormitory.database.repositories.login.LoginRepositories
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val loginRepositories: LoginRepositories) :
    LoginUseCase {
    override fun login(username: String, password: String): Completable {
        return loginRepositories.login(username, password)
    }
}