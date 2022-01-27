package com.example.testzemoga.ui.main.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.domain.Event
import com.example.testzemoga.R
import com.example.testzemoga.databinding.FavoritesFragmentBinding
import com.example.testzemoga.ui.main.PostContainerDirections
import com.example.testzemoga.ui.main.PostListener
import com.example.testzemoga.ui.main.PostsAdapter
import com.example.testzemoga.ui.main.favorites.FavoritesViewModel.FavoritesModel
import com.example.testzemoga.ui.main.favorites.FavoritesViewModel.FavoritesModel.FavoriteListIsEmpty
import com.example.testzemoga.ui.main.favorites.FavoritesViewModel.FavoritesModel.ShowFavoritesPostList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var binding: FavoritesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorites_fragment, container, false)
        binding.viewModel = viewModel
        viewModel.updateFavoritesPosts()

        viewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))
        return binding.root
    }

    private fun changedUI(event: Event<FavoritesModel>) {
        event.getContentIfNotHandled()?.let { model ->
            when (model) {
                is ShowFavoritesPostList -> {
                    binding.listEmpty.visibility = INVISIBLE
                    binding.rvFavoritesPosts.visibility = VISIBLE
                    binding.rvFavoritesPosts.adapter = PostsAdapter(model.postsList, PostListener { post ->
                        this.findNavController().navigate(
                            PostContainerDirections.actionPostContainerToContentFragment(post.id.toString())
                        )
                    })
                }
                is FavoriteListIsEmpty -> {
                    binding.rvFavoritesPosts.visibility = INVISIBLE
                    binding.listEmpty.visibility = VISIBLE
                }
            }
        }
    }


}