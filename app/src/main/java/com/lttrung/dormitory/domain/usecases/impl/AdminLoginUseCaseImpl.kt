package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.repositories.AdminLoginRepositories
import com.lttrung.dormitory.domain.usecases.AdminLoginUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AdminLoginUseCaseImpl @Inject constructor(
    private val adminLoginRepositories: AdminLoginRepositories
) : AdminLoginUseCase {
    override fun execute(username: String, password: String): Single<Unit> {
        return adminLoginRepositories.login(username, password)
    }
}