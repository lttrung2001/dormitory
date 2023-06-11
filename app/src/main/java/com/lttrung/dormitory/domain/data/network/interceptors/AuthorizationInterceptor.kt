package com.lttrung.dormitory.domain.data.network.interceptors

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.lttrung.dormitory.domain.data.local.room.dao.CurrentUserDao
import com.lttrung.dormitory.ui.activities.login.LoginActivity
import com.lttrung.dormitory.utils.AppConstants.ACCESS_TOKEN
import com.lttrung.dormitory.utils.HttpStatusCodes
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
class AuthorizationInterceptor @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val sharedPreferences: SharedPreferences,
    private val currentUserDao: CurrentUserDao,
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        val accessToken = sharedPreferences.getString(ACCESS_TOKEN, "")
        return if (accessToken.isNullOrEmpty()) {
            val loginIntent = Intent(applicationContext, LoginActivity::class.java)
            loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            applicationContext.startActivity(loginIntent)
            throw RuntimeException("Invalid token")

            // Ignore check access token
//            chain.proceed(newBuilder.build())
        } else {
            val newRequest: Request = newBuilder.addHeader("Authorization", accessToken).build()
            val response = chain.proceed(newRequest)
            if (response.code == HttpStatusCodes.UNAUTHORIZED) {
                currentUserDao.deleteCurrentUser()
                applicationContext.startActivity(Intent(applicationContext, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
                throw RuntimeException("Expired or invalid token.")
            } else {
                return response
            }
        }
    }
}