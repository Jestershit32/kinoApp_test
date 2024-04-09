package com.example.movieui.screens.listFavoritsPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.core.utils.Constants
import com.example.movieui.databinding.MovieItemHolderBinding
import com.example.storage.entitys.FavoriteMovie

class FavoritesListAdapter(val onItemClick: (id: Int) -> Unit) :
    ListAdapter<FavoriteMovie, FavoritesListAdapter.ItemHolder>(ItemComparator()) {

    inner class ItemHolder(private val binding: MovieItemHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteMovie) = with(binding) {
            movieName.text = item.title
            movieDescription.text = item.overview
            movieRating.text = item.rating.toString()
            binding.imageView.load(Constants.HEAD_IMG_URL + item.posterPath) {
                crossfade(true)
            }
            binding.root.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<FavoriteMovie>() {
        override fun areItemsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FavoriteMovie,
            newItem: FavoriteMovie
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