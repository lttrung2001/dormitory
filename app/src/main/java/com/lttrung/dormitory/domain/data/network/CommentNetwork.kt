package com.lttrung.dormitory.domain.data.network

import com.lttrung.dormitory.domain.data.network.models.CommentNetworkModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface CommentNetwork {
    fun fetchComments(roomId: Int): Single<List<CommentNetworkModel>>
    fun createComment(commentNetworkModel: CommentNetworkModel): Single<CommentNetworkModel>
    fun deleteComment(commentId: Int): Single<Int>
}