package com.lttrung.dormitory.ui.forgotpassword;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;

@Singleton
public interface ForgotPasswordUseCase {
    Completable forgotPassword(String email);
}
