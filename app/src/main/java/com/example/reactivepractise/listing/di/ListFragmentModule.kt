package com.example.reactivepractise.listing.di

//@Module
//class ListFragmentModule {
//
//    @Provides
//    fun getViewModel(endPoint: RetrofitApiEndPoints): ListViewModel{
//        val factory= object: ViewModelProvider.Factory{
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                return ListViewModel(endPoint) as T
//            }
//        }
//       return ViewModelProviders.of(getApp, factory).get(List::class.java)
//    }
//
//
//}