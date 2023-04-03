package com.lttrung.dormitory.database.data.network.login

import android.content.Context
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.exceptions.UnknownException
import com.lttrung.dormitory.exceptions.UnverifiedEmailException
import com.lttrung.dormitory.utils.HttpStatusCodes
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.io.IOException
import javax.inject.Inject

class LoginRetrofitImpl @Inject constructor(
    private val service: LoginService,
    @ApplicationContext private val context: Context
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
                        else -> throw UnknownException()
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

    override fun verifyCode(): Completable {
        return Completable.complete()
    }

    override fun resetPassword(): Completable {
        return Completable.complete()
    }
}