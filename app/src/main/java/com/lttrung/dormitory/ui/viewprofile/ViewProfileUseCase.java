package com.lttrung.dormitory.ui.viewprofile;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;

@Singleton
public interface ViewProfileUseCase {
    // Bổ sung các thuộc tính có thể edit
    Completable editProfile();
}
