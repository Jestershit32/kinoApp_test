package com.example.movieui.screens.listFavoritsPage

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.presentation.BaseViewModel
import com.example.core.utils.Constants
import com.example.movieui.Screens
import com.example.storage.entitys.FavoriteMovie
import com.example.storage.repository.AppDatabaseRepository
import com.github.terrakok.cicerone.Router
import javax.inject.Inject


class ListFavoritesViewModel @Inject constructor(
    private val router: Router,
    private val databaseRepository: AppDatabaseRepository,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {
    private val _movieListLiveData by lazy {
        MutableLiveData<List<FavoriteMovie>>()
    }
    val movieListLiveData: LiveData<List<FavoriteMovie>>
        get() = _movieListLiveData

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun navigateToDetailFavoriteMovie(id: Int) {
        router.navigateTo(Screens.detailFavoritMoviePage(id))
    }

    fun navigateToListMovie() {
        router.backTo(Screens.movieListPage())
    }

    fun startInit() {
        val favoriteMovie = databaseRepository.getAllFavoriteMovie(
            sharedPreferences.getString(
                Constants.USERNAME, null
            ) ?: ""
        )
        _movieListLiveData.postValue(favoriteMovie)
        _isLoading.value = false
    }
}

