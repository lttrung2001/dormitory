package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.GetRoomTypesUseCase
import com.lttrung.dormitory.domain.usecases.impl.GetRoomTypesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GetRoomTypesUseCaseModule {
    @Binds
    fun bindsGetRoomTypesUseCase(impl: GetRoomTypesUseCaseImpl): GetRoomTypesUseCase
}