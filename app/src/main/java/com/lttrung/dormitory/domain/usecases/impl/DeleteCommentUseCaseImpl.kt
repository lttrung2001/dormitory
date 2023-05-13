package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.repositories.CommentRepositories
import com.lttrung.dormitory.domain.usecases.DeleteCommentUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DeleteCommentUseCaseImpl @Inject constructor(
    private val commentRepositories: CommentRepositories
): DeleteCommentUseCase {
    override fun execute(commentId: Int): Single<Int> {
        return try {
            commentRepositories.deleteComment(commentId)
        } catch (ex: Exception) {
            throw ex
        }
    }
}