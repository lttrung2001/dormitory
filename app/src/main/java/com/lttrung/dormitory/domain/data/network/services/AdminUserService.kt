package com.lttrung.dormitory.domain.data.network.services

import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface AdminUserService {
    @GET("api/manage/analysis/studentInTerm")
    fun fetchStudentStats(): Single<Response<List<StudentStat>>>

    @GET("api/manage/analysis/loaiKTX")
    fun fetchRoomTypeStats(): Single<Response<List<RoomTypeStat>>>

    @GET("api/manage/analysis/gender")
    fun fetchGenderStats(): Single<Response<List<GenderStats>>>
}