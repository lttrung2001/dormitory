package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.local.room.entities.CommentLocalModel
import com.lttrung.dormitory.domain.data.network.models.CommentNetworkModel
import com.lttrung.dormitory.domain.repositories.CommentRepositories
import com.lttrung.dormitory.domain.usecases.WriteCommentUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WriteCommentUseCaseImpl @Inject constructor(
    private val commentRepositories: CommentRepositories
) : WriteCommentUseCase {
    override fun execute(commentNetworkModel: CommentNetworkModel): Single<CommentLocalModel> {
        return try {
            commentRepositories.createComment(commentNetworkModel)
        } catch (ex: Exception) {
            throw ex
        }
    }
}