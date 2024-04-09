package com.example.movieui.screens.detailMoviePage

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.presentation.BaseViewModel
import com.example.core.utils.Constants
import com.example.movieui.Screens
import com.example.networking.domain.models.SimpleMovieInfoByIdDomain
import com.example.networking.repository.MovieRepository
import com.example.storage.entitys.FavoriteMovie
import com.example.storage.repository.AppDatabaseRepository
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
        viewModelScope.launch(Dispatchers.IO) {
            val movieItemByID = databaseRepository.getFavoriteById(id = id, userName = userName)
            try {
                val result = movieRepository.searchMovieById(movieId = id)
                withContext(Dispatchers.Main) {
                    _isFavorite.value = movieItemByID != null
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
            viewModelScope.launch(Dispatchers.IO) {
                databaseRepository.addInFavorite(movie)
                withContext(Dispatchers.Main) {
                    _isFavorite.value = true
                }
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                databaseRepository.removeFavorite(id = movieItem.id, userName = userName)
                withContext(Dispatchers.Main) {
                    _isFavorite.value = false
                }

            }
        }
    }

}