package com.example.movieui

import com.example.movieui.screens.detailFavoritePage.DetailFavoriteFragment
import com.example.movieui.screens.detailMoviePage.DetailMovieFragment
import com.example.movieui.screens.listFavoritsPage.ListFavoritesFragment
import com.example.movieui.screens.listMoviePage.ListMovieFragment
import com.example.movieui.screens.loginPage.LoginFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun movieListPage() = FragmentScreen { ListMovieFragment() }
    fun detailMoviePage(idMovie: Int) = FragmentScreen { DetailMovieFragment.newInstance(idMovie) }
    fun loginPage() = FragmentScreen { LoginFragment() }
    fun favoritesPage() = FragmentScreen { ListFavoritesFragment() }
    fun detailFavoritMoviePage(idMovie: Int) =
        FragmentScreen { DetailFavoriteFragment.newInstance(idMovie) }
}