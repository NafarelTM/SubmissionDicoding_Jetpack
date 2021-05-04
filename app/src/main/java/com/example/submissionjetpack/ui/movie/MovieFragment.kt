package com.example.submissionjetpack.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionjetpack.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let{
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
            val movies = viewModel.getMovies()
            adapter = MovieAdapter()
            adapter.setMovies(movies)

            binding.rvMovie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = this@MovieFragment.adapter
            }
        }
    }
}