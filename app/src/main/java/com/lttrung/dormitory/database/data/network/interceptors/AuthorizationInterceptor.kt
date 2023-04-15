package com.lttrung.dormitory.database.data.network.interceptors

import android.content.Context
import android.content.Intent
import com.lttrung.dormitory.database.data.local.room.dao.CurrentUserDao
import com.lttrung.dormitory.ui.login.LoginActivity
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
    private val currentUserDao: CurrentUserDao,
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        val accessToken: String? = currentUserDao.currentUser?.token
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