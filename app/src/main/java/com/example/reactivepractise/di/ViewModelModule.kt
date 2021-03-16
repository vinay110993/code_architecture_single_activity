package com.example.reactivepractise.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.reactivepractise.listing.viewmodel.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindUserViewModel(viewModel: ListViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelCustomFactory): ViewModelProvider.Factory
}