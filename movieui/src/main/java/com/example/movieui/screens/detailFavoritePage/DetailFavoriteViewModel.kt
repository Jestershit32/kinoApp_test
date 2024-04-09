package com.example.movieui.screens.detailFavoritePage

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

class DetailFavoriteViewModel @Inject constructor(
    private val router: Router,
    private val databaseRepository: AppDatabaseRepository,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    private val _movieInfoLiveData: MutableLiveData<FavoriteMovie> by lazy {
        MutableLiveData<FavoriteMovie>()
    }
    val movieInfoLiveData: LiveData<FavoriteMovie>
        get() = _movieInfoLiveData

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData(false)
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite


    val username = sharedPreferences.getString(Constants.USERNAME, "").orEmpty()

    fun getMovieDetail(id: Int) {
        val movieItemByID = databaseRepository.getFavoriteById(id = id, userName = username)
        _isFavorite.value = movieItemByID != null
        _movieInfoLiveData.value = movieItemByID
        _isLoading.value = false
    }

    fun backToFavoritesPage() {
        router.navigateTo(Screens.favoritesPage())
    }

    fun addOrDeleteInFavorit(movieItem: FavoriteMovie) {

        if (_isFavorite.value == false) {
            val movie =
                FavoriteMovie(
                    idRoom = null,
                    userName = username,
                    title = movieItem.title,
                    id = movieItem.id,
                    overview = movieItem.overview,
                    rating = movieItem.rating,
                    genres = movieItem.genres,
                    runtime = movieItem.runtime,
                    tags = movieItem.tags,
                    posterPath = movieItem.posterPath,
                )
            databaseRepository.addInFavorite(movie)
            _isFavorite.value = true
        } else {
            _isFavorite.value = false
            databaseRepository.removeFavorite(id = movieItem.id, userName = username)

        }


    }

}