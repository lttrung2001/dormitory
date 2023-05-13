package com.lttrung.dormitory.domain.data.network.models

import com.google.gson.annotations.SerializedName
import com.lttrung.dormitory.domain.data.local.room.entities.CommentLocalModel
import java.util.*

open class CommentNetworkModel(
    open val id: Int,
    @SerializedName("mssv") open val studentId: String,
    @SerializedName("idRoom") open val roomId: Int,
    open val content: String,
    open val timePost: Date
) : java.io.Serializable {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }

    fun toCommentLocalModel(studentId: String): CommentLocalModel {
        return CommentLocalModel(
            id,
            studentId,
            roomId,
            content,
            timePost,
            this.studentId == studentId
        )
    }
}