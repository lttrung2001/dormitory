package com.lttrung.dormitory.ui.register

import com.lttrung.dormitory.database.repositories.LoginRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repositories: LoginRepositories
) : RegisterUseCase {
    override fun register(username: String, password: String): Single<String> {
        return repositories.register(username, password)
    }
}