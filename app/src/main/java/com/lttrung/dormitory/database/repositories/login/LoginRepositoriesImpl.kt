package com.lttrung.dormitory.database.repositories.login

import com.lttrung.dormitory.database.data.local.login.LoginLocal
import com.lttrung.dormitory.database.data.network.login.LoginNetwork
import com.lttrung.dormitory.database.data.network.login.LoginResponseBody
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRepositoriesImpl @Inject constructor(
    override val local: LoginLocal,
    override val network: LoginNetwork
) : LoginRepositories {
    override fun login(username: String, password: String): Single<LoginResponseBody> {
        return network.login(username, password)
            .doAfterSuccess { body -> local.login(body) }
    }

    override fun register(): Completable {
        return Completable.complete()
    }

    override fun forgotPassword(): Completable {
        return Completable.complete()
    }

    override fun verifyCode(): Completable {
        return Completable.complete()
    }

    override fun resetPassword(): Completable {
        return Completable.complete()
    }
}