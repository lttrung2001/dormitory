package com.lttrung.dormitory.ui.changepassword

import com.lttrung.dormitory.database.repositories.UserRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ChangePasswordUseCaseImpl @Inject constructor(
    private val userRepositories: UserRepositories
) : ChangePasswordUseCase {
    override fun changePassword(oldPassword: String, newPassword: String): Single<Boolean> {
        return userRepositories.changePassword(oldPassword, newPassword)
    }
}