package com.example.reactivepractise.di

import com.example.reactivepractise.listing.model.GitHubPageResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiEndPoints {
    @GET("search/repositories")
    fun getList(@Query("sort") sort: String,
                @Query("order") order: String,
                @Query("page") page: String,
    @Query("q") query: String?): Observable<GitHubPageResponse>
}