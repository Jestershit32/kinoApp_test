package com.example.kinoapp.screens.detailFavoritePage

import androidx.lifecycle.ViewModel
import com.example.kinoapp.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface DetailFavoriteFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailFavoriteViewModel::class)
    fun bindMedProfileViewModel(viewModel: DetailFavoriteViewModel): ViewModel

}