package com.lttrung.dormitory.domain.repositories

import com.lttrung.dormitory.domain.data.local.UserLocal
import com.lttrung.dormitory.domain.data.local.room.entities.CommentLocalModel
import com.lttrung.dormitory.domain.data.network.CommentNetwork
import com.lttrung.dormitory.domain.data.network.models.CommentNetworkModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface CommentRepositories {
    val network: CommentNetwork
    val userLocal: UserLocal

    fun fetchComments(roomId: Int): Single<List<CommentLocalModel>>
    fun createComment(commentNetworkModel: CommentNetworkModel): Single<CommentLocalModel>
    fun deleteComment(commentId: Int): Single<Int>
}