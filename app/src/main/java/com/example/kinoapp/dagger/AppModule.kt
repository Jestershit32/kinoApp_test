package com.example.kinoapp.dagger

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.kinoapp.App
import com.example.kinoapp.utils.ApplicationContext
import com.example.kinoapp.utils.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
interface AppModule {
    @Binds
    @ApplicationContext
    fun bindApplicationContext(application: App): Context

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}