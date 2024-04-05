package com.example.kinoapp.dagger

import com.example.kinoapp.App
import com.example.kinoapp.NavigationModule
import com.example.kinoapp.localDb.DataBaseModule
import com.example.kinoapp.network.NetworkModule
import com.example.kinoapp.utils.BaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataBaseModule::class,
        ActivitiesModule::class,
        NetworkModule::class,
        NavigationModule::class,
        BaseModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}
