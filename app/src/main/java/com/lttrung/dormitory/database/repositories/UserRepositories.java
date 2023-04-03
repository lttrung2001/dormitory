package com.lttrung.dormitory.database.repositories;

import java.lang.reflect.Type;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Singleton
public interface UserRepositories {
    Single<Type> fetchProfile();
    Completable changePassword();
    Completable editProfile();
}
