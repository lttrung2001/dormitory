package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.domain.repositories.LoginRepositories
import com.lttrung.dormitory.domain.usecases.LoginUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val loginRepositories: LoginRepositories) :
    LoginUseCase {
    override fun execute(username: String, password: String): Single<CurrentUser> {
        return loginRepositories.login(username, password)
    }
}