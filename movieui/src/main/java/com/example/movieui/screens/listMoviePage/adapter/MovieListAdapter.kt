package com.example.movieui.screens.listMoviePage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.core.utils.Constants
import com.example.movieui.databinding.MovieItemHolderBinding
import com.example.networking.domain.models.SimpleMovieInfoDomain

class MovieListAdapter(val onItemClick: (id: Int) -> Unit) :
    ListAdapter<SimpleMovieInfoDomain, MovieListAdapter.ItemHolder>(ItemComparator()) {

    inner class ItemHolder(private val binding: MovieItemHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SimpleMovieInfoDomain) = with(binding) {
            movieName.text = item.title
            movieDescription.text = item.overview
            movieRating.text = item.popularity.toString()
            binding.imageView.load(Constants.HEAD_IMG_URL + item.posterPath) {
                crossfade(true)
            }
            binding.root.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<SimpleMovieInfoDomain>() {
        override fun areItemsTheSame(
            oldItem: SimpleMovieInfoDomain,
            newItem: SimpleMovieInfoDomain
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SimpleMovieInfoDomain,
            newItem: SimpleMovieInfoDomain
        ): Boolean {
            return oldItem == newItem
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