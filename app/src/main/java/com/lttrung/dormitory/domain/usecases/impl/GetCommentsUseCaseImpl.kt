package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.local.room.entities.CommentLocalModel
import com.lttrung.dormitory.domain.repositories.CommentRepositories
import com.lttrung.dormitory.domain.usecases.GetCommentsUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCommentsUseCaseImpl @Inject constructor(
    private val commentRepositories: CommentRepositories
) : GetCommentsUseCase {
    override fun execute(roomId: Int): Single<List<CommentLocalModel>> {
        return try {
            commentRepositories.fetchComments(roomId)
        } catch (ex: Exception) {
            throw ex
        }
    }
}