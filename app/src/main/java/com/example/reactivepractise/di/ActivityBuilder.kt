package com.example.reactivepractise.di;

import com.example.reactivepractise.MainActivity
import com.example.reactivepractise.listing.view.ListFragment
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}

