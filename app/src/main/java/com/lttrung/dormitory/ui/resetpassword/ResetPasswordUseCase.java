package com.lttrung.dormitory.ui.resetpassword;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;

@Singleton
public interface ResetPasswordUseCase {
    Completable resetPassword(String newPassword);
}
