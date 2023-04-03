package com.lttrung.dormitory.database.repositories;

import java.lang.reflect.Type;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;

@Singleton
public interface BillRepositories {

    Single<Type> fetchRoomBills();
    Single<Type> fetchElectricBills();
    Single<Type> fetchWaterBills();
}
