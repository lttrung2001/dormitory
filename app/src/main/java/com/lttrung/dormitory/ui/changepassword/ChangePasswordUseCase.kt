package com.lttrung.dormitory.ui.changepassword

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ChangePasswordUseCase {
    fun changePassword(oldPassword: String, newPassword: String): Single<Boolean>
}