package com.example.kinoapp.screens.listMoviePage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kinoapp.databinding.MovieItemHolderBinding
import com.example.kinoapp.network.models.SimpleMovieInfo
import com.example.kinoapp.utils.Constants

class MovieListAdapter(val onItemClick: (id: Int) -> Unit) :
    ListAdapter<SimpleMovieInfo, MovieListAdapter.ItemHolder>(ItemComparator()) {

    inner class ItemHolder(private val binding: MovieItemHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SimpleMovieInfo) = with(binding) {
            movieName.text = item.title
            movieDescription.text = item.overview
            movieRating.text = item.popularity.toString()
            binding.imageView.load(Constants.HEAD_IMG_URL + item.poster_path) {
                crossfade(true)
            }
            binding.root.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<SimpleMovieInfo>() {
        override fun areItemsTheSame(oldItem: SimpleMovieInfo, newItem: SimpleMovieInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SimpleMovieInfo,
            newItem: SimpleMovieInfo
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            MovieItemHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

}