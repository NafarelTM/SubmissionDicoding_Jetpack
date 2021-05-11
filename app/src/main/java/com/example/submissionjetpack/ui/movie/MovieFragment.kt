package com.example.submissionjetpack.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionjetpack.databinding.FragmentMovieBinding
import com.example.submissionjetpack.viewmodel.MovieViewModel
import com.example.submissionjetpack.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let{
            val vmFactory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, vmFactory).get(MovieViewModel::class.java)

            adapter = MovieAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, {
                binding.aviIndicator.visibility = View.GONE
                adapter.setMovies(it)
                adapter.notifyDataSetChanged()
            })

            binding.rvMovie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = this@MovieFragment.adapter
            }
        }
    }
}