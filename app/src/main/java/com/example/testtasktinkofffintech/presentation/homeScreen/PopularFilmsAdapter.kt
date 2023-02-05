package com.example.testtasktinkofffintech.presentation.homeScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.FilmItem
import com.example.testtasktinkofffintech.R
import com.example.testtasktinkofffintech.databinding.FilmesListItemBinding

class PopularFilmsAdapter(private val listener: Listener): ListAdapter<FilmItem, PopularFilmsAdapter.ItemHolder>(object : DiffUtil.ItemCallback<FilmItem>() {
    override fun areItemsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
        return oldItem == newItem
    }

}) {

    class ItemHolder(private val view: View): RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun setData(item: FilmItem, listener: Listener) {
            val binding = FilmesListItemBinding.bind(view)
            itemView.setOnClickListener {
                listener.onClick()
            }
            binding.tvName.text = item.nameRu
            binding.tvDescription.text = "${item.genres[0].genre} (${item.year})"
            Glide.with(binding.root.context).load(item.posterUrlPreview).centerCrop().into(binding.imFilmPicture)
            // TODO добавить поле избранное, и показывать звезду. проверка на избранное в юзкейсе
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.filmes_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    interface Listener {
        fun onClick()
    }
}