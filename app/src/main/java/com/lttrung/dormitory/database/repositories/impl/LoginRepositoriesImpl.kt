package com.lttrung.dormitory.database.repositories.impl

import com.lttrung.dormitory.database.data.local.LoginLocal
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.database.data.network.LoginNetwork
import com.lttrung.dormitory.database.repositories.LoginRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRepositoriesImpl @Inject constructor(
    override val local: LoginLocal,
    override val network: LoginNetwork
) : LoginRepositories {
    override fun login(username: String, password: String): Single<CurrentUser> {
        return try {
            network.login(username, password).map {
                CurrentUser(it.studentId, password, it.role, it.token)
            }.doOnSuccess { local.login(it) }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun register(username: String, password: String): Single<String> {
        return network.register(username, password)
    }

    override fun forgotPassword(username: String): Single<String> {
        return network.forgotPassword(username)
    }

    override fun verifyCode(username: String, password: String, otp: String): Single<String> {
        return network.verifyCode(username, password, otp)
    }
}