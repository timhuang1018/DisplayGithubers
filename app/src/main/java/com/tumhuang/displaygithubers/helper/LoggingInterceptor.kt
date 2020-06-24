package com.tumhuang.displaygithubers.helper

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * This interceptor is for logging every request
 */
class LoggingInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            Log.d("LoggingInterceptor","request url :${request.url()} ,body:${request.body()}")

            return chain.proceed(request)
        }
}
