package com.lttrung.dormitory.database.repositories.impl

import com.lttrung.dormitory.database.data.local.UserLocal
import com.lttrung.dormitory.database.data.network.UserNetwork
import com.lttrung.dormitory.database.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.database.data.network.models.UserProfile
import com.lttrung.dormitory.database.repositories.UserRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoriesImpl @Inject constructor(
    override val network: UserNetwork, override val local: UserLocal
) :
    UserRepositories {
    override fun fetchProfile(): Single<UserProfile> {
        return network.fetchProfile().doAfterSuccess {
            local.updateUserProfile(it)
        }
    }

    override fun changePassword(oldPassword: String, newPassword: String): Single<Boolean> {
        return network.changePassword(oldPassword, newPassword).doAfterSuccess {
            local.changePassword(newPassword)
        }
    }

    override fun getRoomContract(): Single<FetchRoomContractResponse> {
        return network.getRoomContract().doAfterSuccess {
            local.updateUserProfile(it.userProfile)
        }
    }
}