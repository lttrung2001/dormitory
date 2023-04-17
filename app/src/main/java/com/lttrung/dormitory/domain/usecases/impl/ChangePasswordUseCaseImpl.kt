package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.repositories.UserRepositories
import com.lttrung.dormitory.domain.usecases.ChangePasswordUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ChangePasswordUseCaseImpl @Inject constructor(
    private val userRepositories: UserRepositories
) : ChangePasswordUseCase {
    override fun execute(oldPassword: String, newPassword: String): Single<Unit> {
        return userRepositories.changePassword(oldPassword, newPassword)
    }
}