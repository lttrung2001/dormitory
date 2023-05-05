package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.network.models.StudentStat
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface GetStudentStatsUseCase {
    fun execute(): Single<List<StudentStat>>
}