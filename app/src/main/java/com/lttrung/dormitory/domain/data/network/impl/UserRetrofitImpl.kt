package com.lttrung.dormitory.domain.data.network.impl

import com.lttrung.dormitory.domain.data.network.UserNetwork
import com.lttrung.dormitory.domain.data.network.models.StudentProfile
import com.lttrung.dormitory.domain.data.network.services.UserService
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.utils.HttpStatusCodes
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRetrofitImpl @Inject constructor(
    private val service: UserService
) : UserNetwork {
    override fun fetchProfile(): Single<StudentProfile> {
        return service.fetchUserProfile().map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> response.body()!!
                else -> throw FailedException("Can not get profile.")
            }
        }
    }

    override fun changePassword(oldPassword: String, newPassword: String): Single<Unit> {
        val body = hashMapOf(Pair("oldPassword", oldPassword), Pair("newPassword", newPassword))
        return service.changePassword(body).map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> response.body()!!
                else -> throw FailedException("Can not change password.")
            }
        }
    }
}