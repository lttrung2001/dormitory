package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.network.models.GenderStats
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface GetGenderStatsUseCase {
    fun execute(): Single<GenderStats>
}