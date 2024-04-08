package com.example.kinoapp.screens.detailMoviePage

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kinoapp.Screens
import com.example.kinoapp.localDb.entitys.FavoriteMovie
import com.example.kinoapp.localDb.repository.AppDatabaseRepository
import com.example.kinoapp.network.models.SimpleMovieInfoById
import com.example.kinoapp.network.models.SimpleMovieInfoByIdDomain
import com.example.kinoapp.network.repository.MovieRepository
import com.example.kinoapp.presentation.BaseViewModel
import com.example.kinoapp.utils.Constants
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
    private val router: Router,
    private val movieRepository: MovieRepository,
    private val databaseRepository: AppDatabaseRepository,
    private val sharedPreferences: SharedPreferences,
) : BaseViewModel() {
    private val userName = sharedPreferences.getString(Constants.USERNAME, null) ?: "ADMIN"
//    private val requestToken = sharedPreferences.getString(Constants.REQUEST_TOKEN, null) ?: "ADMIN"

    private val _movieInfoLiveData: MutableLiveData<SimpleMovieInfoByIdDomain> by lazy {
        MutableLiveData<SimpleMovieInfoByIdDomain>()
    }
    val movieInfoLiveData: LiveData<SimpleMovieInfoByIdDomain>
        get() = _movieInfoLiveData

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    private val _isNetwork by lazy {
        MutableLiveData<Boolean>()
    }
    val isNetwork: LiveData<Boolean>
        get() = _isNetwork


    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData(false)
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite


    fun getMovieDetail(id: Int) {
        val movieItemByID = databaseRepository.getFavoriteById(id = id, userName = userName)
        _isFavorite.value = movieItemByID != null
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = movieRepository.searchMovieById(movie_id = id)
                withContext(Dispatchers.Main) {
                    _movieInfoLiveData.postValue(result)
                    _isLoading.value = false
                    _isNetwork.value = true
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _isNetwork.value = false
                }
            }
        }
    }


    fun backInMovieList() {
        router.backTo(Screens.movieListPage())
    }

    fun addOrDeletInFavorit(movieItem: SimpleMovieInfoByIdDomain) {
        if (_isFavorite.value == false) {
            val movie =
                FavoriteMovie(
                    idRoom = null,
                    userName = userName,
                    title = movieItem.title,
                    id = movieItem.id,
                    overview = movieItem.overview,
                    rating = movieItem.popularity,
                    genres = movieItem.genres,
                    runtime = movieItem.runtime,
                    tags = movieItem.tagline,
                    posterPath = movieItem.posterPath
                )
            databaseRepository.addInFavorite(movie)
            _isFavorite.value = true
        } else {
            _isFavorite.value = false
            databaseRepository.removeFavorite(id = movieItem.id, userName = userName)
        }


    }

}