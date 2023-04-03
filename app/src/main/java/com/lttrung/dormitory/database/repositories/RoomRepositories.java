package com.lttrung.dormitory.database.repositories;

import java.lang.reflect.Type;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;

@Singleton
public interface RoomRepositories {
    Single<Type> fetchRoomTypes();
    Single<Type> fetchRooms();
    Single<Type> fetchRoomDetails();
}
