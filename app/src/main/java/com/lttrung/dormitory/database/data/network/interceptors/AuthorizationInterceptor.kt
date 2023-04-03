package com.lttrung.dormitory.database.data.network.interceptors

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.lttrung.dormitory.ui.login.LoginActivity
import com.lttrung.dormitory.utils.AppConstants
import com.lttrung.dormitory.utils.HttpStatusCodes
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val sharedPreferences: SharedPreferences
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        val accessToken = sharedPreferences.getString(AppConstants.ACCESS_TOKEN, "")
        return if (accessToken.isNullOrEmpty()) {
            val loginIntent = Intent(applicationContext, LoginActivity::class.java)
            loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            applicationContext.startActivity(loginIntent)
            throw IOException("Invalid token")
        } else {
            val newRequest: Request = newBuilder.addHeader("Authorization", accessToken).build()
            val response = chain.proceed(newRequest)
            if (response.code == HttpStatusCodes.UNAUTHORIZED) {
                applicationContext.startActivity(Intent(applicationContext, LoginActivity::class.java))
                throw IOException("Expired or invalid token.")
            } else {
                return response
            }
        }
    }
}