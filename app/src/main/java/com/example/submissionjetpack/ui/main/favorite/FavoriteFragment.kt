package com.example.submissionjetpack.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.submissionjetpack.databinding.FragmentFavoriteBinding
import com.example.submissionjetpack.ui.main.home.HomeFragment
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        binding.apply {
            val pagerAdapter = activity?.let { FavoritePagerAdapter(it) }
            binding.viewPager.adapter = pagerAdapter
            TabLayoutMediator(tab, viewPager){ tabs, position ->
                tabs.text = resources.getString(HomeFragment.TAB_TITLES[position])
            }.attach()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}