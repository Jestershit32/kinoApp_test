package com.example.kinoapp.screens.listFavoritsPage.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kinoapp.R
import com.example.kinoapp.databinding.MovieItemHolderBinding
import com.example.kinoapp.localDb.entitys.FavoriteMovie

class FavoritsListAdapter(
    val onItemClick: (id:Int) -> Unit
) :
    RecyclerView.Adapter<FavoritsListAdapter.FavoritsViewHolder>() {

    private val movieList = ArrayList<FavoriteMovie>()

    inner class FavoritsViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = MovieItemHolderBinding.bind(item)

        fun bind(item: FavoriteMovie) = with(binding) {
            movieName.text = item.title
            movieDescription.text = item.overview
            movieRating.text = item.rating.toString()
            binding.imageView.load("https://media.themoviedb.org/t/p/w220_and_h330_face${item.poster_path}"){
                crossfade(true)
            }
            movieItemHolder.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritsViewHolder {
        return FavoritsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item_holder, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: FavoritsViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addMovieList(list: List<FavoriteMovie>) {
        movieList.addAll(list)
        notifyDataSetChanged()
    }
}
