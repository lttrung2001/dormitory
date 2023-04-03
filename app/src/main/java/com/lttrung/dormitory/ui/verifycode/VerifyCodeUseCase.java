package com.lttrung.dormitory.ui.verifycode;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;

@Singleton
public interface VerifyCodeUseCase {
    Completable verifyCode(String code);
}
