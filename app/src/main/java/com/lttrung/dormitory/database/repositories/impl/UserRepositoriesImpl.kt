package com.lttrung.dormitory.database.repositories.impl

import com.lttrung.dormitory.database.data.network.UserNetwork
import com.lttrung.dormitory.database.data.network.models.UserProfileResponse
import com.lttrung.dormitory.database.repositories.UserRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoriesImpl @Inject constructor(override val network: UserNetwork) :
    UserRepositories {
    override fun fetchProfile(): Single<UserProfileResponse> {
        return network.fetchProfile()
    }

    override fun changePassword(): Single<Unit> {
        TODO("Not yet implemented")
    }
}