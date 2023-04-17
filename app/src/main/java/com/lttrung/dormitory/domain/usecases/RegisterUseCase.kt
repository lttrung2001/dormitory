package com.lttrung.dormitory.domain.usecases

import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface RegisterUseCase {
    fun execute(username: String, password: String): Single<Unit>
}