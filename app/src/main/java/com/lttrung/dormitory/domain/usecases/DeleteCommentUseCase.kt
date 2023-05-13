package com.lttrung.dormitory.domain.usecases

import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface DeleteCommentUseCase {
    fun execute(commentId: Int): Single<Int>
}