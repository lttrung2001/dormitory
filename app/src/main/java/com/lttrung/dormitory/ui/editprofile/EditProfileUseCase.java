package com.lttrung.dormitory.ui.editprofile;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;

@Singleton
public interface EditProfileUseCase {
    // Bổ sung các thuộc tính có thể edit
    Completable editProfile();
}
