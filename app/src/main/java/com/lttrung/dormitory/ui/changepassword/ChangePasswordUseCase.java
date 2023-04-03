package com.lttrung.dormitory.ui.changepassword;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;

@Singleton
public interface ChangePasswordUseCase {
    Completable changePassword(String oldPassword, String newPassword);
}
