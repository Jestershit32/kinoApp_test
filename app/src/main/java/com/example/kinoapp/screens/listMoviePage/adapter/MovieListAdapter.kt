package com.example.kinoapp.screens.listMoviePage.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kinoapp.R
import com.example.kinoapp.databinding.MovieItemHolderBinding
import com.example.kinoapp.network.models.SimpleMovieInfo

class MovieListAdapter(
    val onItemClick: (id:Int) -> Unit
) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private val movieList = ArrayList<SimpleMovieInfo>()

    inner class MovieViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = MovieItemHolderBinding.bind(item)

        fun bind(item: SimpleMovieInfo) = with(binding) {
            movieName.text = item.title
            movieDescription.text = item.overview
            movieRating.text = item.popularity.toString()
            binding.imageView.load("https://media.themoviedb.org/t/p/w220_and_h330_face${item.poster_path}"){
                crossfade(true)
            }
            movieItemHolder.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item_holder, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addMovieList(list: List<SimpleMovieInfo>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }
}
