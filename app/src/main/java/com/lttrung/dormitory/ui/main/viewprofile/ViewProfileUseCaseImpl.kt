package com.lttrung.dormitory.ui.main.viewprofile

import com.lttrung.dormitory.database.data.network.models.UserProfile
import com.lttrung.dormitory.database.repositories.UserRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ViewProfileUseCaseImpl @Inject constructor(
    private val userRepositories: UserRepositories
) : ViewProfileUseCase {
    override fun getUserProfile(): Single<UserProfile> {
        return userRepositories.fetchProfile()
    }
}