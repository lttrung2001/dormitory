package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.ui.verifycode.VerifyCodeUseCase
import com.lttrung.dormitory.ui.verifycode.VerifyCodeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface VerifyCodeUseCaseModule {
    @Binds
    fun bindsVerifyCodeUseCase(impl: VerifyCodeUseCaseImpl): VerifyCodeUseCase
}