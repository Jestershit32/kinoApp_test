package com.example.kinoapp.dagger

import com.example.kinoapp.mainActivity.MainActivity
import com.example.kinoapp.mainActivity.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivitiesModule {


    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun contributeToMainActivity(): MainActivity

}