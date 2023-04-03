package com.lttrung.dormitory.ui.register

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface RegisterUseCase {
    fun register(username: String, password: String): Single<String>
}