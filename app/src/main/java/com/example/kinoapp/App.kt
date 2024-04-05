package com.example.kinoapp

import com.example.kinoapp.dagger.AppComponent
import com.example.kinoapp.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class App : DaggerApplication() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        initDagger()
        super.onCreate()
        appComponent.inject(this)
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}