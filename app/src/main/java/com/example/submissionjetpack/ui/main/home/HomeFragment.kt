package com.example.submissionjetpack.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.submissionjetpack.R
import com.example.submissionjetpack.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {
            val pagerAdapter = activity?.let { HomePagerAdapter(it) }
            binding.viewPager.adapter = pagerAdapter
            TabLayoutMediator(tab, viewPager){ tabs, position ->
                tabs.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        @StringRes
        val TAB_TITLES = intArrayOf(
            R.string.tab_movie,
            R.string.tab_tvshow
        )
    }

}