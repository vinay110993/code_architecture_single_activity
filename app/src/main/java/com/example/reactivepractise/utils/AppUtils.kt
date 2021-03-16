package com.example.reactivepractise.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AppUtils {

    fun getRetrofitInstance(): Retrofit {
        val client = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.flickr.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    const val accessTokenAPI =
        "https://api.flickr.com/services/rest/?method=flickr.people.findByUsername&api_key=c01dcb476bb7126dd130992036d66dce&username=adamwlewis&format=json&nojsoncallback=jsonFlickrApi"

}