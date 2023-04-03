package com.lttrung.dormitory.di.provides

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lttrung.dormitory.database.data.local.room.CurrentUserDatabase
import com.lttrung.dormitory.database.data.local.room.dao.CurrentUserDao
import com.lttrung.dormitory.database.data.network.interceptors.AuthorizationInterceptor
import com.lttrung.dormitory.database.data.network.interceptors.NetworkInterceptor
import com.lttrung.dormitory.utils.AppConstants
import com.lttrung.dormitory.utils.RetrofitUtils
import com.lttrung.dormitory.utils.RoomUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {
    @Provides
    @Singleton
    fun providesCurrentUserDatabase(@ApplicationContext context: Context): CurrentUserDatabase {
        return Room.databaseBuilder(
            context,
            CurrentUserDatabase::class.java,
            RoomUtils.CURRENT_USER_DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesCurrentUserDao(db: CurrentUserDatabase): CurrentUserDao {
        return db.currentUserDao()
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    @Named("NoTokenOkHttp")
    fun providesWithoutTokenOkHttp(
        networkInterceptor: NetworkInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @Named("TokenOkHttp")
    fun providesWithTokenOkHttp(
        authorizationInterceptor: AuthorizationInterceptor,
        networksInterceptor: NetworkInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networksInterceptor)
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun providesRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    @Named("TokenRetrofit")
    fun providesTokenRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        @Named("TokenOkHttp") okHttp: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(RetrofitUtils.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .client(okHttp).build()
    }

    @Provides
    @Singleton
    @Named("NoTokenRetrofit")
    fun providesNoTokenRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        @Named("NoTokenOkHttp") okHttp: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(RetrofitUtils.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .client(okHttp).build()
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            AppConstants.DEFAULT_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
    }
}