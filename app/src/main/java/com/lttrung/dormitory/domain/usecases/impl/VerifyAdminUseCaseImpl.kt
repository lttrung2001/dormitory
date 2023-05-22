package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.repositories.AdminLoginRepositories
import com.lttrung.dormitory.domain.usecases.VerifyAdminUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class VerifyAdminUseCaseImpl @Inject constructor(
    private val adminLoginRepositories: AdminLoginRepositories
) : VerifyAdminUseCase {
    override fun execute(username: String, password: String, otp: String): Single<Unit> {
        return adminLoginRepositories.verifyAdmin(username, password, otp).map {}
    }
}