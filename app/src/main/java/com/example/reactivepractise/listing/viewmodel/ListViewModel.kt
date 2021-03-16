package com.example.reactivepractise.listing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.reactivepractise.di.RetrofitApiEndPoints
import com.example.reactivepractise.listing.model.GithubCustomItem
import javax.inject.Inject

class ListViewModel @Inject constructor (private val endPoints: RetrofitApiEndPoints): ViewModel() {

    private var dataSource: LiveData<PageKeyedDataSource<Int, GithubCustomItem>>?=  null
    var dataList: LiveData<PagedList<GithubCustomItem>> ?= null

    fun getAccessToken() {
        val dataFactory = GithubDataFactory(endPoints = endPoints)
        dataSource = dataFactory.getLiveDataSource()
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(10)
            .build()
        dataList = LivePagedListBuilder<Int, GithubCustomItem>(dataFactory, config).build()
    }
}