package com.lttrung.dormitory.domain.data.network.services

import com.lttrung.dormitory.domain.data.network.models.CommentNetworkModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentService {
    @GET("$PATH/room/{roomId}")
    fun fetchComments(@Path("roomId") roomId: Int): Single<Response<List<CommentNetworkModel>>>

    @POST("$PATH/create")
    fun createComment(@Body body: Map<String, String>): Single<Response<List<CommentNetworkModel>>>

    @DELETE("$PATH/delete/{commentId}")
    fun deleteComment(@Path("commentId") commentId: Int): Completable

    companion object {
        private const val PATH = "api/student/comment"
    }
}