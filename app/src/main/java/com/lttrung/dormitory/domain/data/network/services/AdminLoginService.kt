package com.lttrung.dormitory.domain.data.network.services

import com.lttrung.dormitory.domain.data.network.models.StudentStat
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AdminLoginService {
    @POST("api/admin/signin")
    fun login(
        @Body body: Map<String, String>
    ): Single<Response<Unit>>

    @GET("api/analysis/studentInTerm")
    fun fetchStudentStats(@Query("idTerm") term: Int): Single<Response<List<StudentStat>>>
}