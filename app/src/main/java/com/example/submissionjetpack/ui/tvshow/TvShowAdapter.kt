package com.example.submissionjetpack.ui.tvshow

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
import com.example.submissionjetpack.model.entity.TvShowEntity

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val listTvShow = ArrayList<TvShowEntity>()

    fun setTvShow(tvShows: List<TvShowEntity>){
        if(tvShows.isEmpty()) return
        listTvShow.clear()
        listTvShow.addAll(tvShows)
    }

    class TvShowViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity){
            binding.apply {
                tvTitle.text = tvShow.title
                tvDate.text = StringBuilder("${tvShow.date}, ${tvShow.year}")
                ratingBar.rating = tvShow.voteAvg.toFloat()

                Glide.with(itemView.context)
                    .asBitmap()
                    .load(tvShow.posterImg)
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
                    val intent = Intent(itemView.context, TvShowDetailActivity::class.java)
                    intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW_ID, tvShow.id)
                    intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW_TITLE, tvShow.title)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size
}