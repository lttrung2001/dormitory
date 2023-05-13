package com.lttrung.dormitory.domain.data.local.room.entities

import com.lttrung.dormitory.domain.data.network.models.CommentNetworkModel
import java.util.*

data class CommentLocalModel(
    override val id: Int,
    override val studentId: String,
    override val roomId: Int,
    override val content: String,
    override val timePost: Date,
    val isOwner: Boolean
) :
    CommentNetworkModel(
        id, studentId, roomId,
        content,
        timePost
    ) {
    fun toCommentNetworkModel(): CommentNetworkModel {
        return CommentNetworkModel(id, studentId, roomId, content, timePost)
    }
}