package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.UserProfile
import com.lttrung.dormitory.domain.repositories.UserRepositories
import com.lttrung.dormitory.domain.usecases.ViewProfileUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ViewProfileUseCaseImpl @Inject constructor(
    private val userRepositories: UserRepositories
) : ViewProfileUseCase {
    override fun execute(): Single<UserProfile> {
        return userRepositories.fetchProfile()
    }

    override fun logout() {
        return userRepositories.logout()
    }
}