package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.local.room.entities.CommentLocalModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface GetCommentsUseCase {
    fun execute(roomId: Int): Single<List<CommentLocalModel>>
}