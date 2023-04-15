package com.lttrung.dormitory.database.data.network.impl

import com.lttrung.dormitory.database.data.network.UserNetwork
import com.lttrung.dormitory.database.data.network.models.UserProfile
import com.lttrung.dormitory.database.data.network.services.UserService
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.utils.HttpStatusCodes
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRetrofitImpl @Inject constructor(
    private val service: UserService
) : UserNetwork {
    override fun fetchProfile(): Single<UserProfile> {
        return service.fetchUserProfile().map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> response.body()!!
                else -> throw FailedException("Can not get profile.")
            }
        }
    }

    override fun changePassword(oldPassword: String, newPassword: String): Single<Boolean> {
        val body = hashMapOf(Pair("oldPassword", oldPassword), Pair("newPassword", newPassword))
        return service.changePassword(body).map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> response.body()!!
                else -> throw FailedException("Can not change password.")
            }
        }
    }
}