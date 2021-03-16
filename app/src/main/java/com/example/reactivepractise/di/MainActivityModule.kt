package com.example.reactivepractise.di

import com.example.reactivepractise.listing.view.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule{
    @ContributesAndroidInjector
    abstract fun bindListFragment(): ListFragment
}