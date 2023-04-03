package com.lttrung.dormitory.ui.viewroomtypes;

import java.lang.reflect.Type;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;

@Singleton
public interface ViewRoomTypesUseCase {
    // Bổ sung params, thay đổi Type bằng class tương ứng
    Single<Type> getRoomTypes();
}
