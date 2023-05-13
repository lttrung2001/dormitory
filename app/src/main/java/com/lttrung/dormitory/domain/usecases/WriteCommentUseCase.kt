package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.local.room.entities.CommentLocalModel
import com.lttrung.dormitory.domain.data.network.models.CommentNetworkModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface WriteCommentUseCase {
    fun execute(commentNetworkModel: CommentNetworkModel): Single<CommentLocalModel>
}