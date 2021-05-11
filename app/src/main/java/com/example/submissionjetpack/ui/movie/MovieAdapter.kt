package com.example.submissionjetpack.ui.movie

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.submissionjetpack.R
import com.example.submissionjetpack.databinding.ItemMovieBinding
import com.example.submissionjetpack.model.entity.MovieEntity

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>){
        if(movies.isEmpty()) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity){
            binding.apply {
                tvTitle.text = movie.title
                tvDate.text = StringBuilder("${movie.date}, ${movie.year}")
                ratingBar.rating = movie.voteAvg.toFloat()
                Glide.with(itemView.context)
                    .asBitmap()
                    .load(movie.posterImg)
                    .apply(RequestOptions().override(100, 120))
                    .transform(RoundedCorners(16))
                    .into(object: CustomTarget<Bitmap>(){
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            imgPoster.setImageBitmap(resource)
                            setColor(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {

                        }
                    })

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movie.id)
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_TITLE, movie.title)
                    itemView.context.startActivity(intent)
                }
            }
        }

        private fun setColor(image: Bitmap){
            Palette.from(image).generate {
                binding.apply {
                    layoutMovie.setBackgroundColor(it?.vibrantSwatch?.rgb ?: R.color.grey)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tvTitle.setTextColor(itemView.resources.getColor(R.color.white, itemView.context.theme))
                        tvDate.setTextColor(itemView.resources.getColor(R.color.white, itemView.context.theme))
                    }
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