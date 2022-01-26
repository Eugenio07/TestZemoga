package com.example.testzemoga.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.testzemoga.R
import com.example.testzemoga.databinding.PostContainerBinding
import com.example.testzemoga.ui.main.favorites.FavoritesFragment
import com.example.testzemoga.ui.main.posts.PostsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PostContainer : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: PostContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.post_container, container, false)


//        // Declares that this is a home screen. If the back button is pressed, it closes the app
//        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                activity!!.finish()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        binding.payoutsPager.isUserInputEnabled = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager = binding.payoutsPager
        viewPager.isUserInputEnabled = false
        viewPager.adapter = MainCollectionAdapter(this)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = requireActivity().getString(R.string.title_posts)
                1 -> tab.text = requireActivity().getString(R.string.title_favorites)
            }
        }.attach()
    }
}

class MainCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PostsFragment()
            else -> FavoritesFragment()
        }
    }
}