package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.repositories.LoginRepositories
import com.lttrung.dormitory.domain.usecases.RegisterUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repositories: LoginRepositories
) : RegisterUseCase {
    override fun execute(username: String, password: String): Single<Unit> {
        return repositories.register(username, password)
    }
}