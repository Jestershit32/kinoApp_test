package com.example.movieui.activity

import androidx.lifecycle.ViewModel
import com.example.core.utils.FragmentScope
import com.example.core.utils.ViewModelKey
import com.example.movieui.screens.detailFavoritePage.DetailFavoriteFragment
import com.example.movieui.screens.detailFavoritePage.DetailFavoriteFragmentModule
import com.example.movieui.screens.detailMoviePage.DetailMovieFragment
import com.example.movieui.screens.detailMoviePage.DetailMovieFragmentModule
import com.example.movieui.screens.listFavoritsPage.ListFavoritesFragment
import com.example.movieui.screens.listFavoritsPage.ListFavoritesFragmentModule
import com.example.movieui.screens.listMoviePage.ListMovieFragment
import com.example.movieui.screens.listMoviePage.ListMovieFragmentModule
import com.example.movieui.screens.loginPage.LoginFragment
import com.example.movieui.screens.loginPage.LoginFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
interface MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMedProfileViewModel(viewModel: MainViewModel): ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [ListMovieFragmentModule::class])
    fun contributeToListFragment(): ListMovieFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    fun contributeToLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailMovieFragmentModule::class])
    fun contributeToDetailMovieFragment(): DetailMovieFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ListFavoritesFragmentModule::class])
    fun contributeToListFavoritsFragmen(): ListFavoritesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailFavoriteFragmentModule::class])
    fun contributeToDetailFavoriteFragment(): DetailFavoriteFragment

}