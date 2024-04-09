package com.example.kinoapp.dagger

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.kinoapp.App
import com.example.core.utils.ApplicationContext
import com.example.core.utils.ViewModelFactory
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