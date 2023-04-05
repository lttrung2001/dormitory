package com.lttrung.dormitory.database.data.network;

import java.lang.reflect.Type;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;

@Singleton
public interface RoomNetwork {
    Single<Type> fetchRooms();
    Single<Type> fetchRoomDetails();
}
