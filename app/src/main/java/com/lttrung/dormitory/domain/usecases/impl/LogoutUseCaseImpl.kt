package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.repositories.UserRepositories
import com.lttrung.dormitory.domain.usecases.LogoutUseCase
import javax.inject.Inject

class LogoutUseCaseImpl @Inject constructor(
    private val userRepositories: UserRepositories
) : LogoutUseCase {
    override fun execute() {
        return userRepositories.logout()
    }
}