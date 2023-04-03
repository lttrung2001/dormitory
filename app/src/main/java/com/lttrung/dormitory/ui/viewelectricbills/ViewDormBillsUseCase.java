package com.lttrung.dormitory.ui.viewelectricbills;

import java.lang.reflect.Type;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;

@Singleton
public interface ViewDormBillsUseCase {
    // Bổ sung params, thay đổi Type bằng class tương ứng
    Single<Type> getDormBills();
}
