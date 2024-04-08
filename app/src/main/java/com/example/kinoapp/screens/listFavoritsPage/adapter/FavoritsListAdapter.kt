package com.example.kinoapp.screens.listFavoritsPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kinoapp.databinding.MovieItemHolderBinding
import com.example.kinoapp.localDb.entitys.FavoriteMovie
import com.example.kinoapp.utils.Constants

class FavoritsListAdapter(val onItemClick: (id: Int) -> Unit) :
    ListAdapter<FavoriteMovie, FavoritsListAdapter.ItemHolder>(ItemComparator()) {

    inner class ItemHolder(private val binding: MovieItemHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteMovie) = with(binding) {
            movieName.text = item.title
            movieDescription.text = item.overview
            movieRating.text = item.rating.toString()
            binding.imageView.load(Constants.HEAD_IMG_URL + item.poster_path) {
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