package com.lttrung.dormitory.domain.data.network.services

import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.domain.data.network.models.LoginResponse
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AdminLoginService {
    @POST("api/admin/signin")
    fun login(
        @Body body: Map<String, String>
    ): Single<Response<Unit>>

    @POST("api/admin/two-factor-auth")
    fun verifyAdmin(@Body body: Map<String, String>): Single<Response<LoginResponse>>
}