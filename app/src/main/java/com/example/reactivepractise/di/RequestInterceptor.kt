package com.example.reactivepractise.di

import com.example.reactivepractise.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain?): Response {

        val originalRequest = chain?.request()!!
        val url = originalRequest.url()

        val updateUrl = url.newBuilder()
            .addQueryParameter("per_page", AppConstants.PAGE_SIZE)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(updateUrl)
        return chain.proceed(requestBuilder.build())
    }
}