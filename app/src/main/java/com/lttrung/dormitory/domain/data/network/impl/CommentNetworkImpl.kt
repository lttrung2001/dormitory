package com.lttrung.dormitory.domain.data.network.impl

import com.lttrung.dormitory.domain.data.network.CommentNetwork
import com.lttrung.dormitory.domain.data.network.models.CommentNetworkModel
import com.lttrung.dormitory.domain.data.network.services.CommentService
import com.lttrung.dormitory.exceptions.FailedException
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CommentNetworkImpl @Inject constructor(
    private val service: CommentService
) : CommentNetwork {
    override fun fetchComments(roomId: Int): Single<List<CommentNetworkModel>> {
        return service.fetchComments(roomId).map { response ->
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw FailedException(response.message())
            }
        }
    }

    override fun createComment(commentNetworkModel: CommentNetworkModel): Single<CommentNetworkModel> {
        val body = hashMapOf<String, String>()
        body["content"] = commentNetworkModel.content
        return service.createComment(body).map { response ->
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw FailedException(response.message())
            }
        }
    }

    override fun deleteComment(commentId: Int): Single<Int> {
        return service.deleteComment(commentId).toSingle {
            commentId
        }
    }
}