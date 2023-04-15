package com.lttrung.dormitory.database.repositories.impl

import com.lttrung.dormitory.database.data.local.UserLocal
import com.lttrung.dormitory.database.data.network.UserNetwork
import com.lttrung.dormitory.database.data.network.models.UserProfile
import com.lttrung.dormitory.database.repositories.UserRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoriesImpl @Inject constructor(
    override val network: UserNetwork, override val local: UserLocal
) :
    UserRepositories {
    override fun fetchProfile(): Single<UserProfile> {
        return try {
            network.fetchProfile().doAfterSuccess {
                local.updateUserProfile(it)
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun changePassword(oldPassword: String, newPassword: String): Single<Boolean> {
        return try {
            network.changePassword(oldPassword, newPassword).doAfterSuccess {
                local.changePassword(newPassword)
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun logout() {
        local.logout()
    }
}