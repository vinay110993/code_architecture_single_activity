package com.example.reactivepractise.listing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.reactivepractise.di.RetrofitApiEndPoints
import com.example.reactivepractise.listing.model.GithubCustomItem

class GithubDataFactory(val endPoints:RetrofitApiEndPoints): DataSource.Factory<Int, GithubCustomItem>() {
    private val liveDataSource = MutableLiveData<PageKeyedDataSource<Int, GithubCustomItem>>()
    override fun create(): DataSource<Int, GithubCustomItem> {
        val dataSource = GithubListDataSource(endPoints = endPoints)
        liveDataSource.postValue(dataSource)
        return dataSource
    }
    fun getLiveDataSource(): LiveData<PageKeyedDataSource<Int, GithubCustomItem>> = liveDataSource
}