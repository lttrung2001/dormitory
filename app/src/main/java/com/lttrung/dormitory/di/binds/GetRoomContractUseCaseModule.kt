package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.GetRoomContractUseCase
import com.lttrung.dormitory.domain.usecases.impl.GetRoomContractUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GetRoomContractUseCaseModule {
    @Binds
    fun bindsGetRoomContractUseCase(impl: GetRoomContractUseCaseImpl): GetRoomContractUseCase
}