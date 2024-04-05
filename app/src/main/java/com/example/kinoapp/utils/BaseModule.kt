package com.example.kinoapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.kinoapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class BaseModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(application:App): SharedPreferences =
        application.getBaseModuleSharedPreferences()

    @Provides
    @Singleton
    fun provideStringProvider(@ApplicationContext context: Context):StringProvider=
        StringProvider(context)
}