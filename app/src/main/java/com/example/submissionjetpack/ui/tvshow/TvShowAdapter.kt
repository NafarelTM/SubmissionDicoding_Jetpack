package com.example.submissionjetpack.ui.tvshow

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.submissionjetpack.databinding.ItemMovieBinding
import com.example.submissionjetpack.model.DataEntity

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val listTvShow = ArrayList<DataEntity>()

    fun setTvShow(tvShows: List<DataEntity>){
        if(tvShows.isEmpty()) return
        listTvShow.clear()
        listTvShow.addAll(tvShows)
    }

    class TvShowViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: DataEntity){
            binding.apply {
                tvTitle.text = tvShow.title
                tvDate.text = tvShow.date
                Glide.with(itemView.context)
                    .load(tvShow.image)
                    .apply(RequestOptions().override(100, 120))
                    .transform(RoundedCorners(16))
                    .into(imgPoster)

                val palette: Palette = Palette.from(BitmapFactory.decodeResource(itemView.context.resources, tvShow.image)).generate()
                val color: Palette.Swatch? = palette.vibrantSwatch
                color?.let {
                    layoutMovie.setBackgroundColor(color.rgb)
                    tvTitle.setTextColor(color.titleTextColor)
                    tvDate.setTextColor(color.titleTextColor)
                }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, TvShowDetailActivity::class.java)
                    intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW, tvShow)
                    itemView.context.startActivity(intent)
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