package com.example.top250_movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.top250_movielist.databinding.MovieRowLayoutBinding
import com.example.top250_movielist.models.Item

class MovieAdapter(
    private var movieList: List<Item> = emptyList()
): RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: MovieRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val  binding = MovieRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieList = movieList[position]
        holder.bind(movieList)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setData(listItem: List<Item>) {
        movieList = listItem
    }
}