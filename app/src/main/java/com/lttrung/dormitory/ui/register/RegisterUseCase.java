package com.lttrung.dormitory.ui.register;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;

@Singleton
public interface RegisterUseCase {
    // Bổ sung params
    Completable register();
}
