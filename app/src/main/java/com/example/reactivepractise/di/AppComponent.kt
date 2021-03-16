package com.example.reactivepractise.di

import android.app.Application
import com.example.reactivepractise.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AppModule::class, AndroidInjectionModule::class, ActivityBuilder::class, ViewModelModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: MyApplication)
}