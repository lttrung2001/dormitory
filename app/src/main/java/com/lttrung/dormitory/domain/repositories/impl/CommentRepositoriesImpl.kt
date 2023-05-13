package com.lttrung.dormitory.domain.repositories.impl

import com.lttrung.dormitory.domain.data.local.UserLocal
import com.lttrung.dormitory.domain.data.local.room.entities.CommentLocalModel
import com.lttrung.dormitory.domain.data.network.CommentNetwork
import com.lttrung.dormitory.domain.data.network.models.CommentNetworkModel
import com.lttrung.dormitory.domain.repositories.CommentRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CommentRepositoriesImpl @Inject constructor(
    override val network: CommentNetwork, override val userLocal: UserLocal
) : CommentRepositories {
    override fun fetchComments(roomId: Int): Single<List<CommentLocalModel>> {
        return network.fetchComments(roomId).map { networkComments ->
            val studentId = userLocal.getCurrentUser()!!.studentId
            networkComments.map { it.toCommentLocalModel(studentId) }
        }
    }

    override fun createComment(commentNetworkModel: CommentNetworkModel): Single<CommentLocalModel> {
        val studentId = userLocal.getCurrentUser()!!.studentId
        return network.createComment(commentNetworkModel).map { it.toCommentLocalModel(studentId) }
    }

    override fun deleteComment(commentId: Int): Single<Int> {
        network.deleteComment(commentId)
        return Single.just(commentId)
    }
}