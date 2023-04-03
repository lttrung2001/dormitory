package com.lttrung.dormitory.database.data.network.login

import com.lttrung.dormitory.exceptions.UnknownException
import com.lttrung.dormitory.exceptions.UnverifiedEmailException
import com.lttrung.dormitory.utils.HttpStatusCodes
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRetrofitImpl @Inject constructor(private val service: LoginService) : LoginNetwork {
    override fun login(username: String, password: String): Single<LoginResponseBody> {
        return service.login(username, password)
            .map { response ->
                when (response.code()) {
                    HttpStatusCodes.OK -> response.body()!!
                    HttpStatusCodes.FORBIDDEN -> throw UnverifiedEmailException()
                    HttpStatusCodes.BAD_REQUEST -> throw RuntimeException("Verify email failed.")
                    HttpStatusCodes.UNAUTHORIZED -> throw RuntimeException("Wrong email, password.")
                    else -> throw UnknownException()
                }
            }
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