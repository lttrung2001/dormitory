package com.lttrung.dormitory.database.data.network.impl

import com.lttrung.dormitory.database.data.network.LoginNetwork
import com.lttrung.dormitory.database.data.network.responses.LoginResponseBody
import com.lttrung.dormitory.database.data.network.services.LoginService
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.exceptions.UnverifiedEmailException
import com.lttrung.dormitory.utils.HttpStatusCodes
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRetrofitImpl @Inject constructor(
    private val service: LoginService
) : LoginNetwork {
    override fun login(username: String, password: String): Single<LoginResponseBody> {
        return try {
            service.login(username, password)
                .map { response ->
                    when (response.code()) {
                        // Go to main screen
                        HttpStatusCodes.OK -> response.body()!!
                        // Handle to start new activity
                        HttpStatusCodes.FORBIDDEN -> throw UnverifiedEmailException()
                        // Notify error to screen
                        HttpStatusCodes.BAD_REQUEST -> throw FailedException("Verify email failed.")
                        HttpStatusCodes.UNAUTHORIZED -> throw FailedException("Wrong email, password.")
                        else -> {
                            response.body()?.let {
                                throw FailedException()
                            }
                            throw FailedException()
                        }
                    }
                }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun register(username: String, password: String): Single<String> {
        return try {
            service.register(username, password).map { response ->
                when (response.code()) {
                    HttpStatusCodes.OK -> {
                        response.body()!!
                    }
                    else -> {
                        response.body()?.let {
                            throw FailedException(it)
                        }
                        throw FailedException()
                    }
                }
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun forgotPassword(): Completable {
        return Completable.complete()
    }

    override fun verifyCode(username: String, password: String, otp: String): Single<String> {
        return try {
            service.verifyCode(username, password, otp).map { response ->
                when (response.code()) {
                    HttpStatusCodes.OK -> response.body()!!
                    else -> {
                        response.body()?.let { throw FailedException(it) }
                        throw FailedException()
                    }
                }
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun resetPassword(): Completable {
        return Completable.complete()
    }
}