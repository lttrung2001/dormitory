package com.lttrung.dormitory.ui.registerroom;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;

@Singleton
public interface RegisterRoomUseCase {
    // Bổ sung params
    Completable registerRoom();
}
