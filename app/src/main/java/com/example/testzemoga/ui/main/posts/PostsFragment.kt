package com.example.testzemoga.ui.main.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.domain.Event
import com.example.testzemoga.R
import com.example.testzemoga.databinding.PostsFragmentBinding
import com.example.testzemoga.ui.main.posts.PostsViewModel.PostsModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private val viewModel: PostsViewModel by viewModels()
    private lateinit var binding: PostsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.posts_fragment, container, false)
        binding.viewModel = viewModel

        viewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))

        return binding.root
    }

    private fun changedUI(event: Event<PostsModel>) {
        event.getContentIfNotHandled()?.let { model ->
            when (model) {
                is PostsModel.showPostList -> {
                    binding.rvPosts.adapter = PostsAdapter(model.postsList, PostListener { post ->
                        Logger.d(post)
                    })
                }
            }
        }

    }


}