package com.example.movieui.screens.detailFavoritePage

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.example.core.presentation.BaseViewModel
import com.example.core.utils.Constants
import com.example.movieui.Screens
import com.example.storage.entitys.FavoriteMovie
import com.example.storage.repository.AppDatabaseRepository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val _isNetwork by lazy {
        MutableLiveData<Boolean>()
    }
    val isNetwork: LiveData<Boolean>
        get() = _isNetwork

    val username = sharedPreferences.getString(Constants.USERNAME, "").orEmpty()

    fun getMovieDetail(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val movieItemByID = databaseRepository.getFavoriteById(id = id, userName = username)
                withContext(Dispatchers.Main) {
                    _isFavorite.value = movieItemByID != null
                    _movieInfoLiveData.value = movieItemByID
                    _isLoading.value = false
                    _isNetwork.value=true
                }
            }
        } catch (e: Exception) {
            _isNetwork.value=false
        }
    }

    fun backToFavoritesPage() {
        router.navigateTo(Screens.favoritesPage())
    }

    fun addOrDeleteInFavorite(movieItem: FavoriteMovie) {
        try {
            if (_isFavorite.value == false) {
                val movie = FavoriteMovie(
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
                viewModelScope.launch(Dispatchers.IO) {
                    databaseRepository.addInFavorite(movie)
                    withContext(Dispatchers.Main) {
                        _isFavorite.value = true
                        _isNetwork.value=true
                    }
                }
            } else {
                viewModelScope.launch(Dispatchers.IO) {
                    databaseRepository.removeFavorite(id = movieItem.id, userName = username)
                    withContext(Dispatchers.Main) {
                        _isFavorite.value = false
                        _isNetwork.value=true
                    }
                }
            }
        } catch (e: Exception) {
            _isNetwork.value=false
        }
    }

}