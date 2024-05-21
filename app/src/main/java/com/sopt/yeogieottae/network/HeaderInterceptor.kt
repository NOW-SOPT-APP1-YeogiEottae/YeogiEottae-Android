package com.sopt.yeogieottae.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("userId", "1")
            .build()
        return chain.proceed(newRequest)
    }
}