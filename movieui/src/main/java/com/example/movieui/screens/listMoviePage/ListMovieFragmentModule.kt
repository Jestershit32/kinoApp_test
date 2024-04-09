package com.example.movieui.screens.listMoviePage

import androidx.lifecycle.ViewModel
import com.example.core.utils.ViewModelKey
import com.example.movieui.screens.listMoviePage.ListMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ListMovieFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListMovieViewModel::class)
    fun bindMedProfileViewModel(viewModel: ListMovieViewModel): ViewModel

}