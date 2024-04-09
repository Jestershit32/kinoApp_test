package com.example.movieui.screens.listFavoritsPage

import androidx.lifecycle.ViewModel
import com.example.core.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ListFavoritesFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListFavoritesViewModel::class)
    fun bindMedProfileViewModel(viewModel: ListFavoritesViewModel): ViewModel

}