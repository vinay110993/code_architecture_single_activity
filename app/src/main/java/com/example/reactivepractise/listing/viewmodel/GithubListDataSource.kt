package com.example.reactivepractise.listing.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.reactivepractise.di.RetrofitApiEndPoints
import com.example.reactivepractise.listing.model.GithubCustomItem
import com.example.reactivepractise.utils.AppConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubListDataSource(
    private val endPoints: RetrofitApiEndPoints
) : PageKeyedDataSource<Int, GithubCustomItem>() {
    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GithubCustomItem>
    ) {
        endPoints.getList(
            sort = AppConstants.QUERY_SORT,
            order = AppConstants.QUERY_ORDER,
            page = "1",
            query = AppConstants.QUERY_SEARCH
        ).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { pageResponse ->
                val list = mutableListOf<GithubCustomItem>()
                pageResponse.items?.forEach {
                    list.add(
                        GithubCustomItem(
                            id = it?.id ?: 1,
                            name = it?.name,
                            image = it?.owner?.avatarUrl ?: ""
                        )
                    )
                }
                list
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it, null, 2)
            },
                {
                    Log.d("error", it.message ?: "")
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GithubCustomItem>) {
        endPoints.getList(
            sort = AppConstants.QUERY_SORT,
            order = AppConstants.QUERY_ORDER,
            page = params.key.toString(),
            query = AppConstants.QUERY_SEARCH
        )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { pageResponse ->
                val list = mutableListOf<GithubCustomItem>()
                pageResponse.items?.forEach {
                    list.add(
                        GithubCustomItem(
                            id = it?.id ?: 1,
                            name = it?.name ?: "",
                            image = it?.owner?.avatarUrl ?: ""
                        )
                    )
                }
                list
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it, params.key+1)
            },
                {
                    Log.d("error", it.message ?: "")
                })

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GithubCustomItem>
    ) {
    }


}