package com.example.kinoapp.screens.listFavoritsPage

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kinoapp.Screens
import com.example.kinoapp.localDb.Repository.AppDatabaseRepository
import com.example.kinoapp.localDb.entitys.FavoriteMovie
import com.example.kinoapp.presentation.BaseViewModel
import com.example.kinoapp.utils.Constants
import com.github.terrakok.cicerone.Router
import javax.inject.Inject


class ListFavoritsViewModel @Inject constructor(
    private val router: Router,
    private val databaseRepository: AppDatabaseRepository,
    private  val sharedPreferences: SharedPreferences
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
        val favoriteMovie= databaseRepository.getAllFavoriteMovie(sharedPreferences.getString(Constants.USERNAME,null)?:"")
       _movieListLiveData.postValue(favoriteMovie)
        _isLoading.value=false
    }
}

