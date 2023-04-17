package com.lttrung.dormitory.domain.repositories.impl

import com.lttrung.dormitory.domain.data.local.LoginLocal
import com.lttrung.dormitory.domain.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.domain.data.network.LoginNetwork
import com.lttrung.dormitory.domain.repositories.LoginRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRepositoriesImpl @Inject constructor(
    override val local: LoginLocal,
    override val network: LoginNetwork
) : LoginRepositories {
    override fun login(username: String, password: String): Single<CurrentUser> {
        return try {
            network.login(username, password).map {
                CurrentUser(it.studentId, password, it.roles, it.token)
            }.doAfterSuccess { local.login(it) }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun register(username: String, password: String): Single<Unit> {
        return network.register(username, password)
    }

    override fun forgotPassword(username: String): Single<Unit> {
        return network.forgotPassword(username)
    }

    override fun verifyCode(username: String, password: String, otp: String): Single<Unit> {
        return network.verifyCode(username, password, otp)
    }
}