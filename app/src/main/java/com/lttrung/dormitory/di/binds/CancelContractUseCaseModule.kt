package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.CancelContractUseCase
import com.lttrung.dormitory.domain.usecases.impl.CancelContractUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CancelContractUseCaseModule {
    @Binds
    fun bindsCancelContractUseCase(impl: CancelContractUseCaseImpl): CancelContractUseCase
}