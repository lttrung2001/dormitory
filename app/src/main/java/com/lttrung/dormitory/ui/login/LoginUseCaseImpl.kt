package com.lttrung.dormitory.ui.login

import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.database.repositories.LoginRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val loginRepositories: LoginRepositories) :
    LoginUseCase {
    override fun login(username: String, password: String): Single<CurrentUser> {
        return loginRepositories.login(username, password)
    }
}