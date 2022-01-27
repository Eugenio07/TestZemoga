package com.example.testzemoga.ui.main.posts

import android.os.Bundle
import android.view.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.domain.Event
import com.example.testzemoga.R
import com.example.testzemoga.databinding.PostsFragmentBinding
import com.example.testzemoga.ui.main.PostContainerDirections
import com.example.testzemoga.ui.main.PostListener
import com.example.testzemoga.ui.main.PostsAdapter
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
        viewModel.findPosts()
        viewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun changedUI(event: Event<PostsModel>) {
        event.getContentIfNotHandled()?.let { model ->
            when (model) {
                is PostsModel.ShowPostList -> {
                    binding.listEmpty.visibility = INVISIBLE
                    binding.rvPosts.visibility = VISIBLE
                    binding.rvPosts.adapter = PostsAdapter(model.postsList, PostListener { post ->
                        this.findNavController().navigate(
                            PostContainerDirections.actionPostContainerToContentFragment(post.id.toString())
                        )
                    })
                }
                PostsModel.ListIsEmpty -> {
                    binding.rvPosts.visibility = INVISIBLE
                    binding.listEmpty.visibility = VISIBLE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.principal_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.reload -> {
                Logger.d("Update posts")
                viewModel.updatePosts()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}