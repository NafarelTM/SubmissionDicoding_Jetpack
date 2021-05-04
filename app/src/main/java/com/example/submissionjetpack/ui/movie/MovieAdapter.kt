package com.example.submissionjetpack.ui.movie

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.submissionjetpack.databinding.ItemMovieBinding
import com.example.submissionjetpack.model.DataEntity

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val listMovies = ArrayList<DataEntity>()

    fun setMovies(movies: List<DataEntity>){
        if(movies.isEmpty()) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DataEntity){
            binding.apply {
                tvTitle.text = movie.title
                tvDate.text = movie.date
                Glide.with(itemView.context)
                    .load(movie.image)
                    .apply(RequestOptions().override(100, 120))
                    .transform(RoundedCorners(16))
                    .into(imgPoster)

                val palette: Palette = Palette.from(BitmapFactory.decodeResource(itemView.context.resources, movie.image)).generate()
                val color: Palette.Swatch? = palette.vibrantSwatch
                color?.let{
                    layoutMovie.setBackgroundColor(color.rgb)
                    tvTitle.setTextColor(color.titleTextColor)
                    tvDate.setTextColor(color.titleTextColor)
                }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size
}