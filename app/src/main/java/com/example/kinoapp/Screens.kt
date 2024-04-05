package com.example.kinoapp


import com.example.kinoapp.screens.detailFavoritePage.DetailFavoriteFragment
import com.example.kinoapp.screens.detailMoviePage.DetailMovieFragment
import com.example.kinoapp.screens.listFavoritsPage.ListFavoritsFragment
import com.example.kinoapp.screens.listMoviePage.ListMovieFragment
import com.example.kinoapp.screens.loginPage.LoginFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun movieListPage() = FragmentScreen { ListMovieFragment() }
    fun detailMoviePage(idMovie:Int) = FragmentScreen { DetailMovieFragment.newInstance(idMovie)}
    fun loginPage() = FragmentScreen { LoginFragment() }
    fun favoritesPage() = FragmentScreen { ListFavoritsFragment() }
    fun detailFavoritMoviePage(idMovie:Int) = FragmentScreen { DetailFavoriteFragment.newInstance(idMovie)}
}